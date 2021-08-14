package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.FjbBanktype;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author liu
 * @date 2021-07-30
 */
public interface IFjbBanktypeService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param banktypeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FjbBanktype selectFjbBanktypeById(Integer banktypeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fjbBanktype 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FjbBanktype> selectFjbBanktypeList(FjbBanktype fjbBanktype);

    /**
     * 新增【请填写功能名称】
     * 
     * @param fjbBanktype 【请填写功能名称】
     * @return 结果
     */
    public int insertFjbBanktype(FjbBanktype fjbBanktype);

    /**
     * 修改【请填写功能名称】
     * 
     * @param fjbBanktype 【请填写功能名称】
     * @return 结果
     */
    public int updateFjbBanktype(FjbBanktype fjbBanktype);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param banktypeIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbBanktypeByIds(Integer[] banktypeIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param banktypeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbBanktypeById(Integer banktypeId);
}
