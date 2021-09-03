import com.fjb.FjbApplication;
import com.fjb.common.core.domain.entity.SysCity;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.core.domain.entity.SysTown;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.utils.StringUtils;
import com.fjb.system.domain.*;
import com.fjb.system.mapper.*;
import com.fjb.system.service.impl.SysUserServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FjbApplication.class)
public class test {
    @Autowired
    private SysCityMapper sysCityMapper;
    @Autowired
    private SysTownMapper townMapper;
    @Autowired
    private FjbBanktypeMapper banktypeMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private FjbUserinfoMapper userinfoMapper;
    @Autowired
    private SysUserServiceImpl userService;
    @Autowired
    private SysGpsMapper gpsMapper;
    @Autowired
    private TiDanxuanMapper danxuanMapper;
    @Autowired
    private TiDanxuanAnMapper tiDanxuanAnMapper;
    @Autowired
    private DanxuanMapper danMapper;
    @Autowired
    private DanxuanAnMapper anMapper;
    @Autowired
    private DuoxuanMapper duoxuanMapper;
    @Autowired
    private DuoxuanAnMapper duoxuanAnMapper;
    @Autowired
    private PanduanMapper panduanMapper;
    @Autowired
    private PanduanAnMapper panduanAnMapper;



    /**
     * 机构第一层
     */
    @Test
    public void test2(){
        FjbBanktype banktype=new FjbBanktype();
        List<FjbBanktype> list=banktypeMapper.selectFjbBanktypeList(banktype);
        System.out.println(list.size());
        for(int i=0;i<list.size();i++){
            SysDept d=new SysDept();
            d.setDeptName(list.get(i).getBanktypeName());
            d.setParentId((long) 0);
            d.setAncestors("0");
            d.setCreateBy("admin");
            d.setJianCheng(list.get(i).getBanktypeName());
            d.setOrderNum((i+1)+"");
            deptMapper.insertDept(d);
        }
    }

    /**
     * 角色为3
     */
    @Test
    public void test3(){
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(3);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        for(FjbUserinfo F:userinfos){
            SysUser u=new SysUser();
            u.setDeptId((long) 1);
            u.setUserName(F.getUsername());
            u.setPassword(F.getPassword());
            u.setNickName(F.getUsername());
            u.setCreateBy("admin");
            u.setPwdupdatedate(F.getPwdupdatedate());
            Long[] roleIds=new Long[1];
            Long[] postIds=new Long[1];
            roleIds[0]=2L;
            postIds[0]=1L;
            u.setRoleIds(roleIds);
            u.setPostIds(postIds);
            userService.insertUser(u);
        }
    }

    /**
     * 角色为4
     */
    @Test
    public void test4(){
        SysDept dept=new SysDept();
        dept.setParentId((long) 1);
        List<SysDept> list=deptMapper.selectDeptList(dept);
        if(!list.isEmpty()){
            for(SysDept D:list){
                FjbUserinfo userinfo=new FjbUserinfo();
                userinfo.setRoleid(4);
                userinfo.setIsdelete(1);
                userinfo.setBankname(D.getDeptName());
                List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
                for(FjbUserinfo F:userinfos){
                    SysUser u=new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds=new Long[1];
                    Long[] postIds=new Long[1];
                    roleIds[0]=3L;
                    postIds[0]=2L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
                    userService.insertUser(u);
                }
            }
        }
    }

    /**
     * 导入经度纬度
     */
    @Test
    public void test5(){
        SysCity t=new SysCity();
        List<SysCity> towns=sysCityMapper.selectSysCityList(t);
        SysGps g=new SysGps();
        List<SysGps> gps=gpsMapper.selectSysGpsList(g);
        for(SysCity s:towns){
            for(SysGps p:gps){
                if((s.getCname()+"市").equals(p.getName())){
                    String ss[]=p.getGps().split(",");
                    s.setJingDu(ss[1]);
                    s.setWeiDu(ss[0]);
                    sysCityMapper.updateSysCity(s);
                    continue;
                }
            }
        }
    }

    @Test
    public void  test6(){
        SysDept t=new SysDept();
        List<SysDept> depts=deptMapper.selectDeptList(t);
        for(SysDept D:depts){
            SysTown op=townMapper.selectSysTownById(D.getTid());
            if(StringUtils.isNotEmpty(op.getJingDu())){
                D.setJinDu(op.getJingDu());
            }
            if(StringUtils.isNotEmpty(op.getWeiDu())){
                D.setWeiDu(op.getWeiDu());
            }
            deptMapper.updateDept(D);
        }
    }

    /**
     * 添加题目
     */
    @Test
    public void test7(){
        Danxuan danxuan=new Danxuan();
        danxuan.setStatus(0);
        List<Danxuan> danxuans=danMapper.selectDanxuanList(danxuan);
        for(Danxuan D:danxuans){
            TiDanxuan t=new TiDanxuan();
            if(D.getLevel().toString().equals("1")){
                t.setLevel("困难");
            }else if(D.getLevel().toString().equals("2")){
                t.setLevel("普通");
            }else {
                t.setLevel("简单");
            }
            t.setAnswer(D.getAnswer());
            t.setJiexi(D.getJiexi());
            t.setTitle(D.getTitle());
            t.setTitlepic(D.getTitlepic());
            t.setStatus("Y");
            t.setQtype("单选");
            if(D.getQcategory().toString().equals("1")){
                t.setQcategory("基础知识");
            }else if(D.getQcategory().toString().equals("2")||D.getQcategory().toString().equals("3")){
                t.setQcategory("人民币知识");
            }else if(D.getQcategory().toString().equals("6")||D.getQcategory().toString().equals("7")){
                t.setQcategory("其他货币知识");
            }
            t.setCreateBy("admin");
            t.setRemark(D.getId().toString());
            danxuanMapper.insertTiDanxuan(t);
        }
    }

    @Test
    public void test8() {
        TiDanxuan t=new TiDanxuan();
        t.setQtype("单选");
        List<TiDanxuan> ts=danxuanMapper.selectTiDanxuanList(t);
        for(TiDanxuan T:ts){
            DanxuanAn an=new DanxuanAn();
            an.setQid(Long.valueOf(T.getRemark()));
            List<DanxuanAn> ans=anMapper.selectDanxuanAnList(an);
            if(!ans.isEmpty()){
                for(DanxuanAn D:ans){
                    TiDanxuanAn da=new TiDanxuanAn();
                    da.setQid(T.getId());
                    da.setQind(D.getQind());
                    da.setQdetail(D.getQdetail());
                    da.setQpic(D.getQpic());
                    da.setCreateBy("admin");
                    tiDanxuanAnMapper.insertTiDanxuanAn(da);
                }
            }
        }
    }

    /**
     * 添加题目多选
     */
    @Test
    public void test9(){
        Duoxuan danxuan=new Duoxuan();
        danxuan.setStatus(0);
        List<Duoxuan> danxuans=duoxuanMapper.selectDuoxuanList(danxuan);
        for(Duoxuan D:danxuans){
            TiDanxuan t=new TiDanxuan();
            if(D.getLevel().toString().equals("1")){
                t.setLevel("困难");
            }else if(D.getLevel().toString().equals("2")){
                t.setLevel("普通");
            }else {
                t.setLevel("简单");
            }
            t.setAnswer(D.getAnswer());
            t.setJiexi(D.getJiexi());
            t.setTitle(D.getTitle());
            t.setTitlepic(D.getTitlepic());
            t.setStatus("Y");
            t.setQtype("多选");
            if(D.getQcategory().toString().equals("1")){
                t.setQcategory("基础知识");
            }else if(D.getQcategory().toString().equals("2")||D.getQcategory().toString().equals("3")){
                t.setQcategory("人民币知识");
            }else if(D.getQcategory().toString().equals("6")||D.getQcategory().toString().equals("7")){
                t.setQcategory("其他货币知识");
            }
            t.setCreateBy("admin");
            t.setRemark(D.getId().toString());
            danxuanMapper.insertTiDanxuan(t);
        }
    }
    //多选答案
    @Test
    public void test10() {
        TiDanxuan t=new TiDanxuan();
        t.setQtype("多选");
        List<TiDanxuan> ts=danxuanMapper.selectTiDanxuanList(t);
        for(TiDanxuan T:ts){
            DuoxuanAn an=new DuoxuanAn();
            an.setQid(Long.valueOf(T.getRemark()));
            List<DuoxuanAn> ans=duoxuanAnMapper.selectDuoxuanAnList(an);
            if(!ans.isEmpty()){
                for(DuoxuanAn D:ans){
                    TiDanxuanAn da=new TiDanxuanAn();
                    da.setQid(T.getId());
                    da.setQind(D.getQind());
                    da.setQdetail(D.getQdetail());
                    da.setQpic(D.getQpic());
                    da.setCreateBy("admin");
                    tiDanxuanAnMapper.insertTiDanxuanAn(da);
                }
            }
        }
    }

    /**
     * 添加题目判断
     */
    @Test
    public void test11(){
        Panduan danxuan=new Panduan();
        danxuan.setStatus(0);
        List<Panduan> danxuans=panduanMapper.selectPanduanList(danxuan);
        for(Panduan D:danxuans){
            TiDanxuan t=new TiDanxuan();
            if(D.getLevel().toString().equals("1")){
                t.setLevel("困难");
            }else if(D.getLevel().toString().equals("2")){
                t.setLevel("普通");
            }else {
                t.setLevel("简单");
            }
            t.setAnswer(D.getAnswer());
            t.setJiexi(D.getJiexi());
            t.setTitle(D.getTitle());
            t.setTitlepic(D.getTitlepic());
            t.setStatus("Y");
            t.setQtype("判断");
            if(D.getQcategory().toString().equals("1")){
                t.setQcategory("基础知识");
            }else if(D.getQcategory().toString().equals("2")||D.getQcategory().toString().equals("3")){
                t.setQcategory("人民币知识");
            }else if(D.getQcategory().toString().equals("6")||D.getQcategory().toString().equals("7")){
                t.setQcategory("其他货币知识");
            }
            t.setCreateBy("admin");
            t.setRemark(D.getId().toString());
            danxuanMapper.insertTiDanxuan(t);
        }
    }

    //判断答案
    @Test
    public void test12() {
        TiDanxuan t=new TiDanxuan();
        t.setQtype("判断");
        List<TiDanxuan> ts=danxuanMapper.selectTiDanxuanList(t);
        for(TiDanxuan T:ts){
            PanduanAn an=new PanduanAn();
            an.setQid(Long.valueOf(T.getRemark()));
            List<PanduanAn> ans=panduanAnMapper.selectPanduanAnList(an);
            if(!ans.isEmpty()){
                for(PanduanAn D:ans){
                    TiDanxuanAn da=new TiDanxuanAn();
                    da.setQid(T.getId());
                    da.setQind(D.getQind());
                    da.setQdetail(D.getQdetail());
                    da.setQpic(D.getQpic());
                    da.setCreateBy("admin");
                    tiDanxuanAnMapper.insertTiDanxuanAn(da);
                }
            }
        }
    }

    @Test
    public void test13(){
        TiDanxuan t=new TiDanxuan();
        t.setQtype("判断");
        t.setLevel("简单");
        t.setQcategory("基础知识");
        t.setAnswer("A");
        int i= danxuanMapper.insertTiDanxuan(t);
        System.out.println(i);
        System.out.println(t.getId());
    }
}
