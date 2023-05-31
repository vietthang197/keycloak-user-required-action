<#import "template.ftl" as layout>
<@layout.registrationLayout; section>
    <#if section = "title">
        <div class="text-center">
            ${msg("Hoàn tất đăng ký")}
        </div>
    <#elseif section = "form">
        <style>
            .centered-form {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }
        </style>
        <form class="form-horizontal centered-form" role="form" action="${url.loginAction}" method="post">
            <div class="form-group">
                <label for="userType">${msg("Vui lòng chọn loại tài khoản")}</label>
                <select id="userType" name="userType" class="form-control">
                    <option value="">${msg("-- Chọn loại tài khoản bên dưới --")}</option>
                    <option value="company">${msg("Tài khoản công ty, doanh nghiệp")}</option>
                    <option value="employee">${msg("Ứng viên")}</option>
                </select>
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="${msg("Hoàn tất")}">
            </div>
        </form>
    </#if>
</@layout.registrationLayout>