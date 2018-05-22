package cn.studyhuang.cook.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.MaterialMapper;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.pojo.MaterialExample;
import cn.studyhuang.cook.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Resource
	private MaterialMapper materialMapper;
	@Override
	public CallResult saveOrUpdate(Material material) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material get(Long id) {
		// TODO Auto-generated method stub
		return materialMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		materialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Material> listMaterials(Integer page,Integer limit) {
		// TODO Auto-generated method stub
		MaterialExample example = new MaterialExample();
		example.setPage(page);
		example.setPageSize(limit);
		return materialMapper.selectByExample(example);
	}

	@Override
	public List<Material> findMaterialsByMid(Long mid) {
		MaterialExample materialExample = new MaterialExample();
		materialExample.createCriteria().andMenuIdEqualTo(mid);
		return materialMapper.selectByExample(materialExample);
	}

	

}
