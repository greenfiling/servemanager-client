package com.greenfiling.smclient.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.greenfiling.smclient.AccountClient_Manual;
import com.greenfiling.smclient.AttemptClient_Manual;
import com.greenfiling.smclient.CourtCaseClient_Manual;
import com.greenfiling.smclient.CourtClient_Manual;
import com.greenfiling.smclient.EmployeeClient_Manual;
import com.greenfiling.smclient.JobClient_FlexUpload_Manual;
import com.greenfiling.smclient.JobClient_Manual;
import com.greenfiling.smclient.JobClient_Unit_Manual;
import com.greenfiling.smclient.JsonDate_Manual;
import com.greenfiling.smclient.NoteClient_Manual;

@RunWith(Suite.class)

//@formatter:off
@Suite.SuiteClasses({
  AccountClient_Manual.class, 
  AttemptClient_Manual.class, 
  CourtCaseClient_Manual.class, 
  CourtClient_Manual.class,
  EmployeeClient_Manual.class, 
  JobClient_FlexUpload_Manual.class, 
  JobClient_Manual.class, JobClient_Unit_Manual.class, 
  JsonDate_Manual.class,
  NoteClient_Manual.class
})
//@formatter:on

public class AllManual_Suite {

}
