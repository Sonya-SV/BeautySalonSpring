<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img" src="data:image/png;base64,${schedule.master.base64}" alt="..." height="400">
            <div class="form-group">
                <h3>${schedule.master.user.firstName} ${schedule.master.user.lastName}</h3>
            </div>
        </div>
        <div class="col-md-7">
            <#if errorProcedure ??>
                <div class="alert alert-danger"> ${errorProcedure} </div>
            </#if>
            <#if discrepancy??>
                <div class="alert alert-danger"> ${discrepancy} </div>
                <a href="/user/master?masterId=${schedule.master.id}">
                    Choose another procedure
                </a>
            </#if>
            <#list scheduleDate as date>
                <form action="/user/order">
                    <ul class="media-list">
                    <li class="media">
                        <div class="media-body">
                            <div class="medi-heading">
                                <div class="autor"><h4>${date!}</h4></div>
                            </div>
                            <div class="media-text text-justify">
                            <#list availableTime as time>
                                <#assign count=0/>
                                <#list busySchedule as busy>
                                    <#if time == busy.time && date == busy.date && schedule.master.id == busy.master.id>
                                        <label class="radio-label" style="text-decoration: line-through;">
                                            <div class="radio1">
                                                <input class="radio-input" type="time"
                                                       value=${time} name="timeOrder" disabled/>${time!}
                                            </div>
                                        </label>
                                        <#assign count=1/>
                                    </#if>
                                </#list>
                                <#if count <1>
                                    <label class="radio-label">
                                        <div class="radio1">
                                            <input class="radio-input" type="submit" pattern="HH:mm"
                                                   value=${time} name="timeOrder"/>${time!}
                                        </div>
                                    </label>
                                </#if>
                            </#list>
                            </div>
                        </div>
                    </li>
                    </ul>
                    <input type="hidden" value="${date}" name="dateOrder"/>
                </form>
                <hr align="center" width="100%" style="border-color: lightgray"/>
            </#list>
        </div>
    </div>
</div>
</@c.page>