package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysBmb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysZcbMapper;
import com.ruoyi.system.domain.SysZcb;
import com.ruoyi.system.service.ISysZcbService;

/**
 * 注册Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-30
 */
@Service
public class SysZcbServiceImpl implements ISysZcbService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysZcbMapper sysZcbMapper;

    /**
     * 查询注册
     *
     * @param id 注册ID
     * @return 注册
     */
    @Override
    public SysZcb selectSysZcbById(Long id)
    {
        return sysZcbMapper.selectSysZcbById(id);
    }

    /**
     * 查询注册列表
     *
     * @param sysZcb 注册
     * @return 注册
     */
    @Override
    public List<SysZcb> selectSysZcbList(SysZcb sysZcb)
    {
        return sysZcbMapper.selectSysZcbList(sysZcb);
    }

    /**
     * 新增注册
     *
     * @param sysZcb 注册
     * @return 结果
     */
    @Override
    public int insertSysZcb(SysZcb sysZcb)
    {
        sysZcb.setCreateTime(DateUtils.getNowDate());
        return sysZcbMapper.insertSysZcb(sysZcb);
    }

    /**
     * 修改注册
     *
     * @param sysZcb 注册
     * @return 结果
     */
    @Override
    public int updateSysZcb(SysZcb sysZcb)
    {
        sysZcb.setUpdateTime(DateUtils.getNowDate());
        return sysZcbMapper.updateSysZcb(sysZcb);
    }

    /**
     * 批量删除注册
     *
     * @param ids 需要删除的注册ID
     * @return 结果
     */
    @Override
    public int deleteSysZcbByIds(Long[] ids)
    {
        return sysZcbMapper.deleteSysZcbByIds(ids);
    }

    /**
     * 删除注册信息
     *
     * @param id 注册ID
     * @return 结果
     */
    @Override
    public int deleteSysZcbById(Long id)
    {
        return sysZcbMapper.deleteSysZcbById(id);
    }

    @Override
    public String importZcb(List<SysZcb> bmbList, boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(bmbList) || bmbList.size() == 0)
        {
            throw new CustomException("导入报名表数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysZcb bmb : bmbList)
        {
            try
            {
                SysZcb bmb1=new SysZcb();
                bmb1.setName(bmb.getName());
                bmb1.setSfz(bmb.getSfz());
                bmb1.setKaoshiDate(bmb.getKaoshiDate());
                bmb1.setZonghang(bmb.getZonghang());
                // 验证是否存在这个用户
                List<SysZcb> u = sysZcbMapper.selectSysZcbList(bmb1);
                if (u.isEmpty())
                {
                    bmb.setCreateBy(operName);
                    this.insertSysZcb(bmb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + bmb.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bmb.setId(u.get(0).getId());
                    bmb.setUpdateBy(operName);
                    this.updateSysZcb(bmb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + bmb.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + bmb.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " +bmb.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
