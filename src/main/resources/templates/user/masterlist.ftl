<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
    <#import "../parts/pager.ftl" as p/>
<@c.page>

    <div class="container" style="margin-top: 60px">
        <div class="row">
            <h2 style="text-align: center">
                <@spring.message "masters"/>
            </h2>
            <#if page.content??>
                <#list page.content as i>
                    <form action="/user/master/${i.id!}" autocomplete="off" novalidate>
                        <div class="col-sm-3">
                            <div class="panel panel-default">
                                <div class="card">
                                    <div class="card-body" style="text-align: center">
                                        <img class="card-img" src="data:image/png;base64,${i.base64!}" alt="..."
                                             height="300" style=" display: block;margin: 0 auto; margin-top: 5px">
                                        <h5 class="card-title">${i.user.firstName!} ${i.user.lastName!}</h5>
                                        <p class="card-text">${i.timeStart!} - ${i.timeEnd!}</p>
                                        <button type="submit" class="btn btn-primary" style="margin-top: 5px; background: goldenrod">
                                            <@spring.message "choose"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </#list>
            </#if>
        </div>
        <@p.pager url page/>
    </div>

</@c.page>