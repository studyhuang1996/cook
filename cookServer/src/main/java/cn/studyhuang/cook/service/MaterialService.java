package cn.studyhuang.cook.service;

import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Material;


/**
 * 食材
 * @author huang
 *
 *2018年1月9日 下午11:17:02
 */
public interface MaterialService {
   CallResult saveOrUpdate(Material material);
	
    Material get(Long id);
	
	void delete(Long id);
	
	List<Material> listMaterials(Integer page,Integer limit);
	/**
	 * 菜谱id获取菜谱食材
	 * @param mid
	 * @return
	 */
	List<Material> findMaterialsByMid(Long mid);

	

}
