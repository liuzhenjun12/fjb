package com.fjb.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.fjb.common.core.domain.TreeSelect;
import com.fjb.common.core.domain.entity.SysCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysCityMapper;
import com.fjb.system.service.ISysCityService;

/**
 * 城市名称Service业务层处理
 *
 * @author liu
 * @date 2021-08-10
 */
@Service
public class SysCityServiceImpl implements ISysCityService
{
    @Autowired
    private SysCityMapper sysCityMapper;

    /**
     * 查询城市名称
     *
     * @param id 城市名称ID
     * @return 城市名称
     */
    @Override
    public SysCity selectSysCityById(Long id)
    {
        return sysCityMapper.selectSysCityById(id);
    }

    /**
     * 查询城市名称列表
     *
     * @param sysCity 城市名称
     * @return 城市名称
     */
    @Override
    public List<SysCity> selectSysCityList(SysCity sysCity)
    {
        return sysCityMapper.selectSysCityList(sysCity);
    }

    /**
     * 新增城市名称
     *
     * @param sysCity 城市名称
     * @return 结果
     */
    @Override
    public int insertSysCity(SysCity sysCity)
    {
        return sysCityMapper.insertSysCity(sysCity);
    }

    /**
     * 修改城市名称
     *
     * @param sysCity 城市名称
     * @return 结果
     */
    @Override
    public int updateSysCity(SysCity sysCity)
    {
        return sysCityMapper.updateSysCity(sysCity);
    }

    /**
     * 批量删除城市名称
     *
     * @param ids 需要删除的城市名称ID
     * @return 结果
     */
    @Override
    public int deleteSysCityByIds(Long[] ids)
    {
        return sysCityMapper.deleteSysCityByIds(ids);
    }

    /**
     * 删除城市名称信息
     *
     * @param id 城市名称ID
     * @return 结果
     */
    @Override
    public int deleteSysCityById(Long id)
    {
        return sysCityMapper.deleteSysCityById(id);
    }


    @Override
    public List<TreeSelect> buildCityTreeSelect(List<SysCity> list) {
        List<SysCity> cityTrees = buildCityTree(list);
        return cityTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    private List<SysCity> buildCityTree(List<SysCity> list) {
        List<SysCity> returnList = new ArrayList<SysCity>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysCity dept : list)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<SysCity> iterator = list.iterator(); iterator.hasNext();)
        {
            SysCity city = (SysCity) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(city.getParentId()))
            {
                recursionFn(list, city);
                returnList.add(city);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = list;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysCity> list, SysCity t)
    {
        // 得到子节点列表
        List<SysCity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysCity tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<SysCity> getChildList(List<SysCity> list, SysCity t)
    {
        List<SysCity> tlist = new ArrayList<SysCity>();
        Iterator<SysCity> it = list.iterator();
        while (it.hasNext())
        {
            SysCity n = (SysCity) it.next();
            if (n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysCity> list, SysCity t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
