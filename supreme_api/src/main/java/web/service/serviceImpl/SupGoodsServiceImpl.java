package web.service.serviceImpl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import web.dao.SupGoodPhotoDao;
import web.dao.SupGoodsColourDao;
import web.dao.SupGoodsDao;
import web.dao.SupSizeDao;
import web.model.SupGoodPhoto;
import web.model.SupGoods;
import web.model.SupGoodsColour;
import web.model.SupSize;
import web.service.SupGoodsService;
import web.vo.GoodsVo;

@Service
public class SupGoodsServiceImpl implements SupGoodsService {

	private static Logger log = LoggerFactory.getLogger("SupGoodsServiceImpl");

	@Autowired
	private SupGoodsDao goodsDao;

	@Autowired
	private SupSizeDao sizeDao;

	@Autowired
	private SupGoodPhotoDao goodPhotoDao;
	
	@Autowired
	private SupGoodsColourDao  colourDao;
	

	public GoodsVo getById(String id) {
		GoodsVo goodsVo = new GoodsVo();
		try {
			if (StringUtils.isBlank(id)) {
				goodsVo.setCode(1);
				goodsVo.setMsg("参数不能为空");
				return goodsVo;
			}
			SupGoods supGoods = goodsDao.goodsById(id);
			List<SupSize> list = sizeDao.selectByGoodsId(id);
			List<SupGoodPhoto> photoList = goodPhotoDao.photoUrlList(id);
			List<SupGoodsColour>  goodsColourList =  colourDao.selectByGoodsId(id);
			goodsVo.setContent(supGoods.getContent());
			goodsVo.setGoodsId(supGoods.getGoodsId());
			goodsVo.setGoodsName(supGoods.getGoodsName());
			goodsVo.setType(supGoods.getType());
			goodsVo.setGoodsPicture(supGoods.getGoodsPicture());
			goodsVo.setGoodsPrices(supGoods.getGoodsPrices()/100);
			goodsVo.setGoodsSizeList(list);
			goodsVo.setPhotoList(photoList);
			goodsVo.setColourList(goodsColourList);
			goodsVo.setCode(1);
			goodsVo.setMsg("成功");
		} catch (Exception e) {
			goodsVo.setCode(0);
			goodsVo.setMsg("失败");
			log.error("查询商品详情失败:    ",e);
		}
		return goodsVo;
	
	}

	
}
