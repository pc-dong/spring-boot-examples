package cn.dpc.swagger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author dongpeichao
 * @version v1.0
 * @email dongpeichao@boco.com.cn
 * @time 2019年07月11日14:34
 * @modify <BR/>
 * 修改内容：<BR/>
 * 修改人员：<BR/>
 * 修改时间：<BR/>
 */
@ApiModel("Post测试")
@Data
public class PostParam {
	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty(value = "时间",example = "2019-07-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
}
