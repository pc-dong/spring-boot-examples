package cn.dpc.activiti;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.StartProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.NotFoundException;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.CompleteTaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class ActivitiApplicationTests extends AbstractIntegrationTest{

	@Autowired
	private ProcessRuntime processRuntime;

	@Autowired
	private TaskRuntime taskRuntime;

	@WithMockUser(value = "admin",roles = "ACTIVITI_USER")
	@Test
	public void contextLoads() {
		ProcessInstance processInstance = processRuntime.start(new StartProcessPayloadBuilder()
				.withProcessDefinitionKey("apply")
				.withBusinessKey("12222")
				.withVariable("assignee", "admin")
				.build());
		ProcessInstance process = processRuntime.processInstance(processInstance.getId());
		assertNotNull(process);

		Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0,10));
		taskRuntime.complete(new CompleteTaskPayloadBuilder()
				.withTaskId(taskPage.getContent().get(0).getId())
				.build());

		try {
			processRuntime.processInstance(processInstance.getId());
		} catch (Exception e){
			assertTrue(e instanceof NotFoundException);
		}
	}

}
