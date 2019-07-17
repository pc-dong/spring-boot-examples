package cn.dpc.security.repository;


import cn.dpc.security.model.User;

public interface UserRepository extends BaseRepository<User, Long>{
	User getByUsername(String username);
}
