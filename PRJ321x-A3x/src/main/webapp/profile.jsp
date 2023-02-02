<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>User Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
<%@ include file="css/profile.css"%>
</style>
</head>
<body>
<div class="container"> 
        <div class="col-md-12">  
            <div class="col-md-4">      
                <div class="portlet light profile-sidebar-portlet bordered">
                    <div class="profile-userpic">
                        <img src="https://bootdey.com/img/Content/avatar/avatar6.png" class="img-responsive" alt=""> </div>
                    <div class="profile-usertitle">
                        <div class="profile-usertitle-name"> ${detail.name} </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8"> 
                <div class="portlet light bordered">
                    <div class="portlet-title tabbable-line">
                        <div class="caption caption-md">
                            <i class="icon-globe theme-font hide"></i>
                            <span class="caption-subject font-blue-madison bold uppercase">Your info</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div>
                        
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Update</a></li>
                                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
                                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
                                <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
                            </ul>
                        
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="home">
                                    <form action="user-profile" method="post">
                                    	<input type="hidden" class="form-control" name="inputPass" value="${detail.pwd}" placeholder="Name">
                                      <div class="form-group">
                                        <label for="InputEmail">Email address</label>
                                        <input type="email" class="form-control" name="InputEmail" value="${detail.usr}" placeholder="Email" readonly>
                                      </div>
                                      <div class="form-group">
                                        <label for="inputName">Name</label>
                                        <input type="text" class="form-control" name="inputName" value="${detail.name}" placeholder="Name">
                                      </div>
                                      <div class="form-group">
                                        <label for="inputAddress">Address</label>
                                        <input type="text" class="form-control" name="inputAddress" value="${detail.address}" placeholder="Address">
                                      </div>
                                      <div class="form-group">
                                        <label for="inputPhone">Phone</label>
                                        <input type="text" class="form-control" name="inputPhone" value="${detail.phone}" placeholder="Phone">
                                      </div>
                                      <div class="form-group">
                                        <label for="exampleInputFile">File input</label>
                                        <input type="file" id="exampleInputFile">
                                        <p class="help-block">Example block-level help text here.</p>
                                      </div>
                                      <div class="checkbox">
                                        <label>
                                          <input type="checkbox"> Check me out
                                        </label>
                                      </div>
                                      <a href="list" class="btn btn-default" role="button">Cancel</a>
                                      <button type="submit" class="btn btn-success">Submit</button>
                                    </form>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="profile">Profile</div>
                                <div role="tabpanel" class="tab-pane" id="messages">Messages</div>
                                <div role="tabpanel" class="tab-pane" id="settings">Settings</div>
                            </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

</body>
</html>