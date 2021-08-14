import com.fjb.FjbApplication;
import com.fjb.common.core.domain.entity.SysCity;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.system.domain.FjbBanktype;
import com.fjb.system.domain.FjbTown;
import com.fjb.system.domain.FjbUserinfo;
import com.fjb.system.mapper.*;
import com.fjb.system.service.impl.SysUserServiceImpl;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FjbApplication.class)
public class test {
    @Autowired
    private SysCityMapper sysCityMapper;
    @Autowired
    private FjbTownMapper townMapper;
    @Autowired
    private FjbBanktypeMapper banktypeMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private FjbUserinfoMapper userinfoMapper;
    @Autowired
    private SysUserServiceImpl userService;

    /**
     * 导入县
     */
    @Test
    public void test1(){
        SysCity city=new SysCity();
        List<SysCity> list=sysCityMapper.selectSysCityList(city);
        for(SysCity C:list){
            if(!C.getCityType().equals("1")){
                FjbTown town=new FjbTown();
                town.setCid(C.getCode());
                List<FjbTown> fjbTowns=townMapper.selectFjbTownList(town);
                if(!fjbTowns.isEmpty()){
                    for(int i=0;i<fjbTowns.size();i++) {
                        SysCity city1 = new SysCity();
                        city1.setCode(fjbTowns.get(i).getTid().toString());
                        city1.setName(fjbTowns.get(i).getTname());
                        city1.setCityType("3");
                        city1.setParentId(C.getId());
                        city1.setOrderNum((i+1)+"");
                        sysCityMapper.insertSysCity(city1);
                    }
                }
            }
        }
    }

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
            d.setDeptType("1");
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
     * 角色为5的机构
     */
    @Test
    public void test5(){
        SysDept d1=new SysDept();
        d1.setDeptType("2");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(5);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoListGroup(userinfo);
        if(!userinfos.isEmpty()){
            System.out.println(userinfos.size());
            System.out.println(depts.size());
                for(SysDept D:depts){
                    int i=0;
                    for(FjbUserinfo F:userinfos){
                        if(F.getCityid().toString().equals(D.getJinDu())){
                            i++;
                            SysDept d=new SysDept();
                            d.setDeptName(F.getBankname());
                            d.setParentId(D.getDeptId());
                            d.setAncestors(D.getAncestors()+","+D.getDeptId());
                            d.setSheng("贵州省");
                            SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
                            d.setShi(D.getShi());
                            d.setXian(xian.getName());
                            d.setDeptType("3");
                            d.setCreateBy("admin");
                            d.setJinDu(D.getJinDu());
                            d.setWeiDu(F.getTownid().toString());
                            d.setJianCheng(F.getBankname());
                            d.setOrderNum((i)+"");
                            deptMapper.insertDept(d);
                        }
                    }
            }
        }
    }

    /**
     * 角色为5的用户
     */
    @Test
    public void  test5_user(){
        SysDept d1=new SysDept();
        d1.setDeptType("3");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(5);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        if(!userinfos.isEmpty()){
            for(FjbUserinfo F:userinfos){
                for(SysDept D:depts){
                    if(F.getCityid().toString().equals(D.getJinDu())&&F.getTownid().toString().equals(D.getWeiDu())){
                        SysUser u=new SysUser();
                        u.setDeptId(D.getDeptId());
                        u.setUserName(F.getUsername());
                        u.setPassword(F.getPassword());
                        u.setNickName(F.getUsername());
                        u.setCreateBy("admin");
                        u.setPwdupdatedate(F.getPwdupdatedate());
                        Long[] roleIds=new Long[1];
                        Long[] postIds=new Long[1];
                        roleIds[0]=4L;
                        postIds[0]=3L;
                        u.setRoleIds(roleIds);
                        u.setPostIds(postIds);
                        userService.insertUser(u);
                    }
                }

            }

        }
    }
    /**
     * 角色为6的机构
     */
    @Test
    public void test6(){
        SysDept d1=new SysDept();
        d1.setDeptType("1");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(6);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        List distinctByUniqueList = userinfos.stream()

                .filter(item -> !depts.stream()

                        .map(e -> e.getWeiDu())

                        .collect(Collectors.toList())

                        .contains(item.getTownid().toString()))

                .collect(Collectors.toList());
        System.out.println(distinctByUniqueList.size());
        for(Object F:distinctByUniqueList){
            System.out.println(F);
        }
    }
    /**
     * 角色为6的用户
     */
    @Test
    public void  test6_user(){
        SysDept d1=new SysDept();
        d1.setDeptType("3");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(6);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        if(!userinfos.isEmpty()){
            for(FjbUserinfo F:userinfos){
                for(SysDept D:depts){
                    if(F.getCityid().toString().equals(D.getJinDu())&&F.getTownid().toString().equals(D.getWeiDu())){
                        SysUser u=new SysUser();
                        u.setDeptId(D.getDeptId());
                        u.setUserName(F.getUsername());
                        u.setPassword(F.getPassword());
                        u.setNickName(F.getUsername());
                        u.setCreateBy("admin");
                        u.setPwdupdatedate(F.getPwdupdatedate());
                        Long[] roleIds=new Long[1];
                        Long[] postIds=new Long[1];
                        roleIds[0]=8L;
                        postIds[0]=4L;
                        u.setRoleIds(roleIds);
                        u.setPostIds(postIds);
                        userService.insertUser(u);
                    }
                }

            }

        }
    }
    /**
     * 角色为7的机构
     */
    @Test
    public void test7(){
        SysDept d1=new SysDept();
        d1.setDeptType("1");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(7);
        userinfo.setIsdelete(1);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        for(FjbUserinfo F:userinfos) {
            FjbBanktype banktype=banktypeMapper.selectFjbBanktypeById(F.getBanktypeid());
            for (SysDept D : depts) {
              if(banktype.getBanktypeName().equals(D.getDeptName())){
                  D.setSheng("贵州省");
                  SysCity shi=sysCityMapper.selectByCode(F.getCityid().toString());
                  SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
                  D.setShi(shi.getName());
                  D.setXian(xian.getName());
                  deptMapper.updateDept(D);
                  SysUser u=new SysUser();
                  u.setDeptId(D.getDeptId());
                  u.setUserName(F.getUsername());
                  u.setPassword(F.getPassword());
                  u.setNickName(F.getUsername());
                  u.setCreateBy("admin");
                  u.setPwdupdatedate(F.getPwdupdatedate());
                  Long[] roleIds=new Long[1];
                  Long[] postIds=new Long[1];
                  roleIds[0]=5L;
                  postIds[0]=5L;
                  u.setRoleIds(roleIds);
                  u.setPostIds(postIds);
                  userService.insertUser(u);
              }
            }
        }

    }

    /**
     * 角色为8的机构
     */
    @Test
    public void test8(){
        SysDept D=deptMapper.selectDeptById((long) 81);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(8);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(83);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        int i=0;
        for(FjbUserinfo F:userinfos) {
            i++;
            SysDept d=new SysDept();
            d.setDeptName(F.getBankname().replace("分行","支行"));
            d.setParentId(D.getDeptId());
            d.setAncestors(D.getAncestors()+","+D.getDeptId());
            d.setSheng("贵州省");
            SysCity shi=sysCityMapper.selectByCode(F.getCityid().toString());
            SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
            d.setShi(shi.getName());
            d.setXian(xian.getName());
//            d.setDeptName("中国银行"+shi.getName()+"支行");
            d.setDeptType("2");
            d.setAddress(F.getBranchaddress());
            d.setCreateBy("admin");
                if(!StringUtils.isEmpty(F.getBranchlongitude())&&!StringUtils.isEmpty(F.getBranchlatitude())) {
                    if (F.getBranchlongitude().length() > 7) {
                        d.setJinDu(F.getBranchlongitude().substring(0, 7));
                    } else {
                        d.setJinDu(F.getBranchlongitude());
                    }
                    if (F.getBranchlatitude().length() > 7) {
                        d.setWeiDu(F.getBranchlatitude().substring(0, 7));
                    } else {
                        d.setWeiDu(F.getBranchlatitude());
                    }
                }
//            d.setJianCheng("中国银行"+shi.getName()+"支行");
            if(F.getIsidentify().toString().equals("0")){
                d.setLeader("N");
            }else {
                d.setLeader("Y");
            }
            d.setJianCheng(F.getBankname().replace("分行","支行"));
            d.setOrderNum((i)+"");
            d.setJigouCode(F.getBranchcode());
            d.setYinCode(F.getSealCoding());
            d.setWangType(F.getCityid().toString());
            d.setWangCode(F.getTownid().toString());
//            System.out.println(d.toString());
            deptMapper.insertDept(d);
        }
        test8_user();
    }

    /**
     * 角色为8的用户
     */
    @Test
    public void test8_user(){
        System.out.println("=============================================================");
        SysDept d1=new SysDept();
        d1.setDeptType("2");
        d1.setParentId((long) 81);
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(8);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(83);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        int i=0;
        for(FjbUserinfo F:userinfos) {
            for(SysDept D:depts) {
                if(F.getTownid().toString().equals(D.getWangCode())) {
                    SysUser u = new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds = new Long[1];
                    Long[] postIds = new Long[1];
                    roleIds[0] = 6L;
                    postIds[0] = 6L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
                    userService.insertUser(u);
                }
            }
        }
    }

    /**
     * 角色为9的机构
     */
    @Test
    public void test9() {
        SysDept d1 = new SysDept();
        d1.setDeptType("2");
        d1.setParentId((long) 41);
        List<SysDept> depts = deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo = new FjbUserinfo();
        userinfo.setRoleid(9);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(41);
        List<FjbUserinfo> userinfos = userinfoMapper.selectFjbUserinfoList(userinfo);
        if (!userinfos.isEmpty()) {
            System.out.println(userinfos.size());
            System.out.println(depts.size());
            for (SysDept D : depts) {
                int i = 0;
                for (FjbUserinfo F : userinfos) {
                    if (F.getCityid().toString().equals(D.getWangType())) {
                        i++;
                        SysDept d=new SysDept();
                        d.setHang("41");
                        d.setDeptName(F.getBankname().replace("管理员",""));
                        d.setParentId(D.getDeptId());
                        d.setAncestors(D.getAncestors()+","+D.getDeptId());
                        d.setSheng("贵州省");
                        SysCity shi=sysCityMapper.selectByCode(F.getCityid().toString());
                        SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
                        d.setShi(shi.getName());
                        d.setXian(xian.getName());
//                        d.setDeptName(xian.getName()+"农信社");
                        d.setDeptType("3");
                        d.setAddress(F.getBranchaddress());
                        d.setCreateBy("admin");
                        if(!StringUtils.isEmpty(F.getBranchlongitude())&&!StringUtils.isEmpty(F.getBranchlatitude())) {
                            if (F.getBranchlongitude().length() > 7) {
                                d.setJinDu(F.getBranchlongitude().substring(0, 7));
                            } else {
                                d.setJinDu(F.getBranchlongitude());
                            }
                            if (F.getBranchlatitude().length() > 7) {
                                d.setWeiDu(F.getBranchlatitude().substring(0, 7));
                            } else {
                                d.setWeiDu(F.getBranchlatitude());
                            }
                        }
//                        d.setJianCheng(xian.getName()+"农信社");
                        if(F.getIsidentify().toString().equals("0")){
                            d.setLeader("N");
                        }else {
                            d.setLeader("Y");
                        }
                        d.setJianCheng(F.getBankname().replace("管理员",""));
                        d.setOrderNum((i)+"");
                        d.setJigouCode(F.getBranchcode());
                        d.setYinCode(F.getSealCoding());
                        d.setWangType(F.getCityid().toString());
                        d.setWangCode(F.getTownid().toString());
//                        System.out.println(d.toString());
                        deptMapper.insertDept(d);
                    }
                }
            }
        }
        test9_user();
    }

    /**
     * 角色为9的用户
     */
    @Test
    public void test9_user(){
        System.out.println("==========================hang===================================");
        SysDept d1=new SysDept();
        d1.setDeptType("3");
        d1.setHang("41");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(9);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(41);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        int i=0;
        for(FjbUserinfo F:userinfos) {
            for(SysDept D:depts) {
                if(F.getTownid().toString().equals(D.getWangCode())) {
                    SysUser u = new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds = new Long[1];
                    Long[] postIds = new Long[1];
                    roleIds[0] = 7L;
                    postIds[0] = 7L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
//                    System.out.println(u.toString());
                    userService.insertUser(u);
                }
            }
        }
    }

    /**
     * 角色为10的机构
     */
    @Test
    public void test10() {
        SysDept d1 = new SysDept();
        d1.setDeptType("2");
        d1.setParentId((long) 13);
        List<SysDept> depts = deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo = new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        List<FjbUserinfo> userinfos = userinfoMapper.selectFjbUserinfoList(userinfo);
        if (!userinfos.isEmpty()) {
            System.out.println(userinfos.size());
            System.out.println(depts.size());
            for (SysDept D : depts) {
                int i = 0;
                for (FjbUserinfo F : userinfos) {
                    if (F.getCityid().toString().equals(D.getWangType())) {
                        i++;
                        SysDept d=new SysDept();
                        d.setHang("13");
                        d.setDeptName(F.getBankname());
                        d.setParentId(D.getDeptId());
                        d.setAncestors(D.getAncestors()+","+D.getDeptId());
                        d.setSheng("贵州省");
                        SysCity shi=sysCityMapper.selectByCode(F.getCityid().toString());
                        SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
                        d.setShi(shi.getName());
                        d.setXian(xian.getName());
                        d.setDeptType("3");
                        d.setAddress(F.getBranchaddress());
                        d.setCreateBy("admin");
                        if(!StringUtils.isEmpty(F.getBranchlongitude())&&!StringUtils.isEmpty(F.getBranchlatitude())) {
                            if (F.getBranchlongitude().length() > 7) {
                                d.setJinDu(F.getBranchlongitude().substring(0, 7));
                            } else {
                                d.setJinDu(F.getBranchlongitude());
                            }
                            if (F.getBranchlatitude().length() > 7) {
                                d.setWeiDu(F.getBranchlatitude().substring(0, 7));
                            } else {
                                d.setWeiDu(F.getBranchlatitude());
                            }
                        }
//                        d.setJianCheng(xian.getName()+"农信社");
                        if(F.getIsidentify().toString().equals("0")){
                            d.setLeader("N");
                        }else {
                            d.setLeader("Y");
                        }
                        d.setJianCheng(F.getBankname());
                        d.setOrderNum((i)+"");
                        d.setJigouCode(F.getBranchcode());
                        d.setYinCode(F.getSealCoding());
                        d.setWangType(F.getCityid().toString());
                        d.setWangCode(F.getTownid().toString());
//                        System.out.println(d.toString());
                        deptMapper.insertDept(d);
                    }
                }
            }
        }
        test10_user();
    }

    /**
     * 角色为10的用户
     */
    @Test
    public void test10_user(){
        System.out.println("==========================hang===================================");
        SysDept d1=new SysDept();
        d1.setDeptType("3");
        d1.setHang("13");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        System.out.println(userinfos.size());
        for(FjbUserinfo F:userinfos) {
            for(SysDept D:depts) {
                if(F.getTownid().toString().equals(D.getWangCode())&&F.getCityid().toString().equals(D.getWangType())
                &&F.getBankname().equals(D.getDeptName())) {
                    SysUser u = new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds = new Long[1];
                    Long[] postIds = new Long[1];
                    roleIds[0] = 9L;
                    postIds[0] = 8L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
//                    System.out.println(u.toString());
                    userService.insertUser(u);
                    continue;
                }
            }
        }
    }

    /**
     * 角色为10_有县的机构
     */
    @Test
    public void test10_2() {
        SysDept d1 = new SysDept();
        d1.setDeptType("3");
        d1.setHang("13");
        List<SysDept> depts = deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo = new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        List<FjbUserinfo> userinfos = userinfoMapper.selectFjbUserinfoList(userinfo);
        if (!userinfos.isEmpty()) {
            System.out.println(userinfos.size());
            System.out.println(depts.size());
            for (SysDept D : depts) {
                int i = 0;
                for (FjbUserinfo F : userinfos) {
                    if (F.getCityid().toString().equals(D.getWangType())&&F.getTownid().toString().equals(D.getWangCode())) {
                        i++;
                        SysDept d=new SysDept();
                        d.setHang("13");
                        d.setDeptName(F.getBankname());
                        d.setParentId(D.getDeptId());
                        d.setAncestors(D.getAncestors()+","+D.getDeptId());
                        d.setSheng("贵州省");
                        SysCity shi=sysCityMapper.selectByCode(F.getCityid().toString());
                        SysCity xian=sysCityMapper.selectByCode(F.getTownid().toString());
                        d.setShi(shi.getName());
                        d.setXian(xian.getName());
                        d.setDeptType("4");
                        d.setAddress(F.getBranchaddress());
                        d.setCreateBy("admin");
                        if(!StringUtils.isEmpty(F.getBranchlongitude())&&!StringUtils.isEmpty(F.getBranchlatitude())) {
                            if (F.getBranchlongitude().length() > 7) {
                                d.setJinDu(F.getBranchlongitude().substring(0, 7));
                            } else {
                                d.setJinDu(F.getBranchlongitude());
                            }
                            if (F.getBranchlatitude().length() > 7) {
                                d.setWeiDu(F.getBranchlatitude().substring(0, 7));
                            } else {
                                d.setWeiDu(F.getBranchlatitude());
                            }
                        }
//                        d.setJianCheng(xian.getName()+"农信社");
                        if(F.getIsidentify().toString().equals("0")){
                            d.setLeader("N");
                        }else {
                            d.setLeader("Y");
                        }
                        d.setJianCheng(F.getBankname());
                        d.setOrderNum((i)+"");
                        d.setJigouCode(F.getBranchcode());
                        d.setYinCode(F.getSealCoding());
                        d.setWangType(F.getCityid().toString());
                        d.setWangCode(F.getTownid().toString());
//                        System.out.println(d.toString());
                        deptMapper.insertDept(d);
                    }
                }
            }
        }
//        test10_user_1();
    }

    /**
     * 角色为10的用户
     */
    @Test
    public void test10_user_1(){
        System.out.println("==========================hang===================================");
        SysDept d1=new SysDept();
        d1.setDeptType("4");
        d1.setHang("13");
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        System.out.println(userinfos.size());
        for(FjbUserinfo F:userinfos) {
            for(SysDept D:depts) {
                if(F.getTownid().toString().equals(D.getWangCode())&&F.getCityid().toString().equals(D.getWangType())
                        &&F.getBankname().equals(D.getDeptName())) {
                    SysUser u = new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds = new Long[1];
                    Long[] postIds = new Long[1];
                    roleIds[0] = 9L;
                    postIds[0] = 8L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
//                    System.out.println(u.toString());
                    userService.insertUser(u);
                    continue;
                }
            }
        }
    }

    /**
     * 角色为10_没有县的机构
     */
    @Test
    public void test10_2_1() {
        SysDept D = deptMapper.selectDeptById((long) 2122);
        FjbUserinfo userinfo = new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        userinfo.setCityid(520100);
        userinfo.setTownid(520103);
        List<FjbUserinfo> userinfos = userinfoMapper.selectFjbUserinfoList(userinfo);
        if (!userinfos.isEmpty()) {
            System.out.println(userinfos.size());
            int i = 0;
            for (FjbUserinfo F : userinfos) {
                    i++;
                    SysDept d = new SysDept();
                    d.setHang("13");
                    d.setDeptName(F.getBankname());
                    d.setParentId(D.getDeptId());
                    d.setAncestors(D.getAncestors() + "," + D.getDeptId());
                    d.setSheng("贵州省");
                    SysCity shi = sysCityMapper.selectByCode(F.getCityid().toString());
                    SysCity xian = sysCityMapper.selectByCode(F.getTownid().toString());
                    d.setShi(shi.getName());
                    d.setXian(xian.getName());
                    d.setDeptType("4");
                    d.setAddress(F.getBranchaddress());
                    d.setCreateBy("admin");
                    if (!StringUtils.isEmpty(F.getBranchlongitude()) && !StringUtils.isEmpty(F.getBranchlatitude())) {
                        if (F.getBranchlongitude().length() > 7) {
                            d.setJinDu(F.getBranchlongitude().substring(0, 7));
                        } else {
                            d.setJinDu(F.getBranchlongitude());
                        }
                        if (F.getBranchlatitude().length() > 7) {
                            d.setWeiDu(F.getBranchlatitude().substring(0, 7));
                        } else {
                            d.setWeiDu(F.getBranchlatitude());
                        }
                    }
//                        d.setJianCheng(xian.getName()+"农信社");
                    if (F.getIsidentify().toString().equals("0")) {
                        d.setLeader("N");
                    } else {
                        d.setLeader("Y");
                    }
                    d.setJianCheng(F.getBankname());
                    d.setOrderNum((i) + "");
                    d.setJigouCode(F.getBranchcode());
                    d.setYinCode(F.getSealCoding());
                    d.setWangType(F.getCityid().toString());
                    d.setWangCode(F.getTownid().toString());
//                        System.out.println(d.toString());
                    deptMapper.insertDept(d);
                }
//        test10_user_1_1();
        }
    }
    /**
     * 角色为10的用户
     * 2223
     */
    @Test
    public void test10_user_1_1(){
        System.out.println("==========================hang===================================");
        SysDept d1=new SysDept();
        d1.setDeptType("4");
        d1.setHang("13");
        d1.setParentId((long) 2122);
        List<SysDept> depts=deptMapper.selectDeptList(d1);
        FjbUserinfo userinfo=new FjbUserinfo();
        userinfo.setRoleid(10);
        userinfo.setIsdelete(1);
        userinfo.setBanktypeid(13);
        userinfo.setCityid(520100);
        userinfo.setTownid(520103);
        List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
        System.out.println(userinfos.size());
        for(FjbUserinfo F:userinfos) {
            for(SysDept D:depts) {
                if(F.getTownid().toString().equals(D.getWangCode())&&F.getCityid().toString().equals(D.getWangType())
                        &&F.getBankname().equals(D.getDeptName())) {
                    SysUser u = new SysUser();
                    u.setDeptId(D.getDeptId());
                    u.setUserName(F.getUsername());
                    u.setPassword(F.getPassword());
                    u.setNickName(F.getUsername());
                    u.setCreateBy("admin");
                    u.setPwdupdatedate(F.getPwdupdatedate());
                    Long[] roleIds = new Long[1];
                    Long[] postIds = new Long[1];
                    roleIds[0] = 9L;
                    postIds[0] = 8L;
                    u.setRoleIds(roleIds);
                    u.setPostIds(postIds);
//                    System.out.println(u.toString());
                    userService.insertUser(u);
                    continue;
                }
            }
        }
    }
}
