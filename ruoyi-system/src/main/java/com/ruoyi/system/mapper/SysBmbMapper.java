package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBmb;
import com.ruoyi.system.domain.vo.CountUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 报名Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-29
 */
public interface SysBmbMapper
{
    /**
     * 查询报名
     *
     * @param id 报名ID
     * @return 报名
     */
    public SysBmb selectSysBmbById(Long id);

    /**
     * 查询报名列表
     *
     * @param sysBmb 报名
     * @return 报名集合
     */
    public List<SysBmb> selectSysBmbList(SysBmb sysBmb);

    /**
     * 分组查询列表
     * @param pici
     * @return
     */
    public List<SysBmb> selectSysBmbListGroup(String pici);

    /**
     * 新增报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    public int insertSysBmb(SysBmb sysBmb);

    /**
     * 修改报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    public int updateSysBmb(SysBmb sysBmb);

    /**
     * 删除报名
     *
     * @param id 报名ID
     * @return 结果
     */
    public int deleteSysBmbById(Long id);

    /**
     * 批量删除报名
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBmbByIds(Long[] ids);

    /**
     * 批量查询
     * @param ids
     * @return
     */
    public List<SysBmb> selectSysBmbByIds(Long[] ids);

    /**
     * 通过批次查询各行的报名人数
     * @param pici
     * @return
     */
    @Select({"<script> SELECT d.jian_cheng AS jigou, d.dept_id as id, b.pici AS pici, count(b.pici) as zongshu,sum(if(b.sfwc='Y',1,0)) AS shi,sum(if(b.sfwc='N',1,0)) AS bu FROM "
            +" sys_bmb b LEFT JOIN sys_dept d ON b.dept_id = d.dept_id GROUP BY b.dept_id HAVING b.pici=#{pici} "
            +"</script>"
            })
    public List<CountUserVo> coutsBypici(@Param("pici") String pici);
}
