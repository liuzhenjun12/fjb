package com.fjb.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.FjbBanktypeMapper;
import com.fjb.system.domain.FjbBanktype;
import com.fjb.system.service.IFjbBanktypeService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liu
 * @date 2021-07-30
 */
@Service
public class FjbBanktypeServiceImpl implements IFjbBanktypeService
{
    @Autowired
    private FjbBanktypeMapper fjbBanktypeMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param banktypeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FjbBanktype selectFjbBanktypeById(Integer banktypeId)
    {
        return fjbBanktypeMapper.selectFjbBanktypeById(banktypeId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param fjbBanktype 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FjbBanktype> selectFjbBanktypeList(FjbBanktype fjbBanktype)
    {
        return fjbBanktypeMapper.selectFjbBanktypeList(fjbBanktype);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param fjbBanktype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFjbBanktype(FjbBanktype fjbBanktype)
    {
        return fjbBanktypeMapper.insertFjbBanktype(fjbBanktype);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param fjbBanktype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFjbBanktype(FjbBanktype fjbBanktype)
    {
        return fjbBanktypeMapper.updateFjbBanktype(fjbBanktype);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param banktypeIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFjbBanktypeByIds(Integer[] banktypeIds)
    {
        return fjbBanktypeMapper.deleteFjbBanktypeByIds(banktypeIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param banktypeId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFjbBanktypeById(Integer banktypeId)
    {
        return fjbBanktypeMapper.deleteFjbBanktypeById(banktypeId);
    }
}
