const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  nickName:state => state.user.nickName,
  introduction: state => state.user.introduction,
  roles: state => state.user.roles,
  roleName:state => state.user.roleName,
  deptName:state => state.user.deptName,
  phone:state => state.user.contactNumber,
  userId:state => state.user.userId,
  deptId:state => state.user.deptId,
  permissions: state => state.user.permissions,
  permission_routes: state => state.permission.routes
}
export default getters
