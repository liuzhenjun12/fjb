import com.fjb.FjbApplication;
import com.fjb.common.core.domain.entity.SysCity;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.core.domain.entity.SysTown;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.utils.StringUtils;
import com.fjb.system.domain.FjbBanktype;
import com.fjb.system.domain.FjbTown;
import com.fjb.system.domain.FjbUserinfo;
import com.fjb.system.domain.SysGps;
import com.fjb.system.mapper.*;
import com.fjb.system.service.impl.SysUserServiceImpl;
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
}
