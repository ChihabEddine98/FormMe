package track

import grails.transaction.Transactional
import track.User;
import track.Role;
import track.UserRole;

@Transactional
class UserRoleService {

  def assignRole(String pAuthority, User pUserInstance)
  {
    def role = Role.findByAuthority(pAuthority)
    if (!role)
    {
      return false
    }
    if (!pUserInstance.authorities.contains(role))
    {
      UserRole.create pUserInstance, role
      //log.info "User $pUserInstance.email has been assigned to the role $pAuthority."
    }

    return true
  }
}
