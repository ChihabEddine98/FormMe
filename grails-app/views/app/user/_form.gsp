<%@ page import="track.User" %>
            <div class="form-input form-firstname">
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstname', 'error')}">
                    <g:textField type="text" name="firstname" value="${userInstance?.firstname}"
                                                    placeholder="Firstname"/>
                </div>
            </div>

            <div class="form-input form-lastname">
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastname', 'error')}">
                    <g:textField type="text" name="lastname" value="${userInstance?.lastname}"
                                                    placeholder="Lastname"/>
                </div>
            </div>

            <div class="form-input form-email">
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')}">
                    <g:textField type="text" name="email" value="${userInstance?.email}" placeholder="Email"/>
                </div>
            </div>

            <div class="form-input form-password">
                <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')}">
                    <input type='password' type='password' name="password" value="${userInstance?.password}"
                           placeholder="Password*"/>
                </div>
            </div>


