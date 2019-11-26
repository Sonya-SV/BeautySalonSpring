<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<div class="container" style="margin-top: 100px">
    <div class="row">
        <#if procedures ??>
            <div class="col-sm-6">
                <img class="card-img" src="data:image/png;base64,${procedures[0].category.base64!}" alt="..." height="400" style=" display: block; margin: 0 auto; margin-top: 5px">
            </div>
            <div class="col-sm-6">
                <h2>${procedures[0].category.name!}</h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                        <th><@spring.message "name"/></th>
                        <th><@spring.message "price"/></th>
                    </tr>
                    <#list procedures as i>
                        <tr>
                            <td>${i.name!}</td>
                            <td>${i.price!}</td>
                        </tr>
                    </#list>
                </table>
            </div>
        </#if>
    </div>
</div>
    <#include "masterlist.ftl">
</@c.page>