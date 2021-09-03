package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.PanduanAn;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-30
 */
public interface PanduanAnMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public PanduanAn selectPanduanAnById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param panduanAn 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PanduanAn> selectPanduanAnList(PanduanAn panduanAn);

    /**
     * 新增【请填写功能名称】
     * 
     * @param panduanAn 【请填写功能名称】
     * @return 结果
     */
    public int insertPanduanAn(PanduanAn panduanAn);

    /**
     * 修改【请填写功能名称】
     * 
     * @param panduanAn 【请填写功能名称】
     * @return 结果
     */
    public int updatePanduanAn(PanduanAn panduanAn);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePanduanAnById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePanduanAnByIds(Long[] ids);
}
