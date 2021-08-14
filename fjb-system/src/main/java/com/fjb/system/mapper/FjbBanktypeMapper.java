package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.FjbBanktype;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liu
 * @date 2021-07-30
 */
public interface FjbBanktypeMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param banktypeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FjbBanktype selectFjbBanktypeById(Integer banktypeId);

    /**
     * 查询【请填写功能名称】
     *
     * @param banktypeName 【请填写功能名称】名称
     * @return 【请填写功能名称】
     */
    public FjbBanktype selectFjbBanktypeByName(String banktypeName);

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
     * 删除【请填写功能名称】
     *
     * @param banktypeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbBanktypeById(Integer banktypeId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param banktypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFjbBanktypeByIds(Integer[] banktypeIds);
}
