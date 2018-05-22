package cn.studyhuang.cook.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.ast.Call;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.MenuMapper;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.MenuExample;
import cn.studyhuang.cook.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
    
	@Resource
	private MenuMapper menuMapper;	
	@Override
	public CallResult saveOrUpdate(Menu menu) {
		CallResult result = new CallResult();
		if (menu.getMid() == null) {
			menu.setMdate(new Date());
			menuMapper.insert(menu);
		}else {
			menu.setMdate(new Date());
			menuMapper.updateByPrimaryKeySelective(menu);
		} 
		return result;
	}

	@Override
	public void deleteById(Long uid) {
		 menuMapper.deleteByPrimaryKey(uid);
	}

	@Override
	public Menu get(Long mid) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(mid);
	}

	
	@Override
	public List<Menu> getMenus(Integer startRow,Integer pageSize) {
		// TODO Auto-generated method stub
		MenuExample menuExample = new MenuExample();
//		menuExample.createCriteria().andMnameLike("%"+mname+"%");
		menuExample.setPage(startRow);
		return menuMapper.selectByExampleWithBLOBs(menuExample);
	}

	@Override
	public List<Menu> getMenusByName(String mname) {
	if(StringUtils.isEmpty(mname)) {
		return null;
	}
	
	MenuExample menuExample = new MenuExample();
	menuExample.createCriteria().andMnameLike("%"+mname+"%");
	return menuMapper.selectByExampleWithBLOBs(menuExample);
   }

	@Override
	public List<Menu> getMenusByCid(Long cid) {
		if (StringUtils.isEmpty(cid)) {
			return null;
		}
		MenuExample menuExample = new MenuExample();
		menuExample.createCriteria().andCidEqualTo(cid);
		return menuMapper.selectByExample(menuExample);
	}

	@Override
	public List<Menu> getMenusByUserId(Long userId) {
		if (StringUtils.isEmpty(userId)) {
			return null;
		}
		MenuExample menuExample = new MenuExample();
		menuExample.createCriteria().andUseridEqualTo(userId);
		return menuMapper.selectByExample(menuExample);
	}

	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return menuMapper.selectByExampleWithBLOBs(null);
	}
	
	
}