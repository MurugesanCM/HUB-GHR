package action_Requests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.BaseClass;

public class Approve_Reject_In_Approvals extends BaseClass {

@BeforeMethod
public void invokeBrowser() throws IOException, InterruptedException
{
// Start Chromedriver
driver = initializeDriver();
}
@Test
public void approveRecordInListView() throws InterruptedException
{
	actionRequest.ApproveRecordListView("APPROVALS","Approved");
}
@Test
public void rejectRecordInListView() throws InterruptedException
{
	actionRequest.RejectRecordListView("APPROVALS","Rejected");
}
@Test
public void rejectRecordInViewScreen() throws InterruptedException
{
	actionRequest.RejectRecord("APPROVALS", "Rejected");
}
@Test
public void ViewRecord() throws InterruptedException
{
	actionRequest.ViewRecord("APPROVALS");
}
}
