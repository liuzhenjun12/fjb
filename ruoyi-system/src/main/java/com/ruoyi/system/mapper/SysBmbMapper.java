package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBmb;
import com.ruoyi.system.domain.vo.*;
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
    @Select({"<script> SELECT  b.sheng_name as sheng,d.jian_cheng AS jigou, d.dept_id as id, b.pici AS pici, count(b.pici) as zongshu,sum(if(b.sfwc='Y',1,0)) AS shi,sum(if(b.sfwc='N',1,0)) AS bu, "
            +" sum(if(b.liluen='Y',1,0)) AS liluenshi,sum(if(b.liluen='N',1,0)) AS liluenbu,sum(if(b.liluen='Q',1,0)) AS liluenque,sum(if(b.shichao='Y',1,0)) AS shichaoshi, "
            +" CONCAT(ROUND(sum(if(b.liluen='Y',1,0)) / sum(IF(b.sfwc='Y',1,0)) * 100, 0),'','%') as liluenhegeli, DATE_FORMAT(b.kaoshi_time,'%Y-%m-%d') AS kaoshitime, "
            +" CONCAT(ROUND(sum(if(b.shichao='Y',1,0)) / sum(IF(b.sfwc='Y',1,0)) * 100, 0),'','%') as shicaohegeli "
            +"  FROM sys_bmb b LEFT JOIN sys_dept d ON b.dept_id = d.dept_id GROUP BY b.dept_id HAVING b.pici=#{pici} "
            +"</script>"
            })
    public List<CountUserVo> coutsBypici(@Param("pici") String pici);

    /**
     * 首页统计列表
     * @return
     */
    @Select({"<script> SELECT b.sheng_name as sheng,d.jian_cheng AS jigou,DATE_FORMAT(b.kaoshi_time,'%Y-%m-%d') as kaoshitime,sum(IF(b.sfwc='Y',1,0)) AS shi,sum(if(b.liluen='Y',1,0)) AS liluenshi, "
            +" sum(IF(b.liluen = 'N', 1, 0)) AS  liluenbu,sum(if(b.liluen='Q',1,0)) AS liluenque,sum(if(b.shichao='Y',1,0)) AS shichaoshi FROM sys_bmb b "
            +" LEFT JOIN sys_dept d ON b.dept_id = d.dept_id GROUP BY b.dept_id,b.kaoshi_time HAVING date_format(b.kaoshi_time,'%Y') =#{name} and b.sheng_name=#{idcard} ORDER BY b.kaoshi_time desc "
            +"</script>"
           })
    public List<CountUserVo> findAllList(@Param("name") String name,@Param("idcard") String idcard);

    /**
     * 首页地图统计数据
     * @return
     */
    @Select({"<script> SELECT b.sheng_name AS jigou,sum(IF(b.sfwc='Y',1,0)) AS shi,sum(if(b.liluen='Y',1,0)) AS liluenshi, "
            +"  sum(if(b.liluen='N',1,0)) AS liluenbu,sum(if(b.liluen='Q',1,0)) AS liluenque,sum(if(b.shichao='Y',1,0)) AS shichaoshi  "
            +" FROM sys_bmb b GROUP BY b.sheng_id "
            +"</script>"
           })
    public List<CountUserVo> findShengCount();

    /**
     * 通过批次机构查询考试日期id
     * @param pici
     * @param deptId
     * @return
     */
    @Select({"<script> select exam_id from sys_bmb where pici=#{pici} and dept_id=#{deptId} GROUP BY exam_id </script>" })
    public List<String> selectExml(@Param("pici") String pici,@Param("deptId") Long deptId);

    /**
     * 统计所有测评人数、合格人数
     * @return
     */
    @Select({"<script> select count(1) as xueyuan, sum(IF(b.liluen = 'Y', 1, 0)) AS hegers from sys_bmb b where b.sfwc = 'Y' </script>" })
    public CountAll countXueyuan();

    /**
     * 统计销售金额，订单数量
     * @return
     */
    @Select({"<script> SELECT sum(b.num) as dingdan,sum(b.money)AS jine from sys_product b </script>" })
    public CountAll countProductAll();

    /**
     * 统计城市销售订单个数，金额
     * @return
     */
    @Select({"<script> select c.shi as name, CONVERT(c.jingdu, DECIMAL(18,2)) as jindu,CONVERT(c.weidu, DECIMAL(18,2)) as weidu," +
            " sum(p.num) as num,sum(p.money) as money from sys_product p LEFT JOIN sys_customer c ON p.cid = c.id GROUP BY c.shi </script>" })
    public List<JingduVo> countOrder();

    /**
     * 统计城市销售订单个数，金额,线性图
     * @return
     */
    @Select({"<script> select sum(b.num) AS num,sum(b.money) AS money, DATE_FORMAT(b.send_time, '%m') AS name" +
            " FROM  sys_product b where date_format(b.send_time, '%Y') = #{nian} GROUP BY DATE_FORMAT(b.send_time, '%m') </script>" })
    public List<JingduVo> countOrderXian(@Param("nian") String nian);

    /**
     * 统计回访,线性图
     * @return
     */
    @Select({"<script> select count(1) AS num,sum(IF(b.yao_mai = '还想购买', 1, 0)) AS money,sum(IF(b.tai_du = '非常满意', 1, 0)) AS value,DATE_FORMAT(b.create_time, '%m') AS name " +
            " FROM  sys_huifan b where date_format(b.create_time, '%Y') = #{nian} GROUP BY DATE_FORMAT(b.create_time, '%m') </script>" })
    public List<JingduVo> countHuifanXian(@Param("nian") String nian);

    /**
     * 统计各省报名线性图
     * @return
     */
    @Select({"<script> select sum(IF(b.sfwc = 'Y', 1, 0)) AS num,DATE_FORMAT(b.kaoshi_time, '%m') AS name " +
            " FROM  sys_bmb b where date_format(b.kaoshi_time, '%Y') = #{nian} and b.sheng_name=#{sheng} GROUP BY DATE_FORMAT(b.kaoshi_time, '%m') </script>" })
    public List<JingduVo> countBaoXian(@Param("nian") String nian,@Param("sheng") String sheng);

    /**
     * 统计客户行业
     * @return
     */
    @Select({"<script> select  SUM(IF(b.industry = '1', 1, 0)) as gr,SUM(IF(b.industry = '2', 1, 0)) as qy, " +
            "  SUM(IF(b.industry = '3', 1, 0)) as yh,SUM(IF(b.industry = '4', 1, 0)) as yy FROM sys_customer b </script>" })
    public CountQy countQy();

    /**
     * 统计客户行业
     * @return
     */
    @Select({"<script> select b.sheng_name AS name, sum(IF(b.sfwc = 'Y', 1, 0)) AS value " +
            "  FROM sys_bmb b GROUP BY b.sheng_name </script>" })
    public List<NameValue> countSheng();

    /**
     * 统计客户购买大于1个箱柜锁的
     * @return
     */
    @Select({"<script> select b.cname AS name,sum(b.num) AS value " +
            "  FROM sys_product b GROUP BY b.cname HAVING value>1 </script>" })
    public List<NameValue> countMai();
}
