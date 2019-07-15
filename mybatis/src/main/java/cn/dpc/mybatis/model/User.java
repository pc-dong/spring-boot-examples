package cn.dpc.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "user_info")
public class User implements Serializable {
    @Id
    private Integer id;

    private String username;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}