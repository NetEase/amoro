(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a830a942"],{2240:function(e,t,a){"use strict";a.r(t);var o=a("7a23");const c={class:"border-wrap"},n={class:"resource-wrap"},r={class:"content"};function l(e,t,a,l,i,s){const u=Object(o["resolveComponent"])("TableList"),p=Object(o["resolveComponent"])("a-tab-pane"),d=Object(o["resolveComponent"])("List"),b=Object(o["resolveComponent"])("a-button"),m=Object(o["resolveComponent"])("a-tabs"),O=Object(o["resolveComponent"])("GroupModal");return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",c,[Object(o["createElementVNode"])("div",n,[Object(o["createElementVNode"])("div",r,[Object(o["createVNode"])(m,{activeKey:e.activeTab,"onUpdate:activeKey":t[1]||(t[1]=t=>e.activeTab=t),destroyInactiveTabPane:"",onChange:e.onChangeTab},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(p,{key:"tables",tab:e.t("tables"),class:Object(o["normalizeClass"])(["tables"===e.activeTab?"active":""])},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(u,{curGroupName:"all",type:"tables"})]),_:1},8,["tab","class"]),Object(o["createVNode"])(p,{key:"optimizers",tab:e.t("optimizers"),class:Object(o["normalizeClass"])(["optimizers"===e.activeTab?"active":""])},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(d,{type:"optimizers"})]),_:1},8,["tab","class"]),Object(o["createVNode"])(p,{key:"optimizergroup",tab:e.t("optimizergroup"),class:Object(o["normalizeClass"])(["optimizergroup"===e.activeTab?"active":""])},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(b,{type:"primary",class:"g-mb-16",onClick:t[0]||(t[0]=t=>e.editGroup(null))},{default:Object(o["withCtx"])(()=>[Object(o["createTextVNode"])(Object(o["toDisplayString"])(e.t("addgroup")),1)]),_:1}),(Object(o["openBlock"])(),Object(o["createBlock"])(d,{key:e.groupKeyCount,type:"optimizergroup",onEditGroup:e.editGroup},null,8,["onEditGroup"]))]),_:1},8,["tab","class"])]),_:1},8,["activeKey","onChange"])])]),e.showGroupModal?(Object(o["openBlock"])(),Object(o["createBlock"])(O,{key:0,edit:e.groupEdit,editRecord:e.groupEditRecord,onCancel:t[2]||(t[2]=t=>e.showGroupModal=!1),onRefresh:t[3]||(t[3]=t=>{e.groupKeyCount++,e.showGroupModal=!1})},null,8,["edit","editRecord"])):Object(o["createCommentVNode"])("",!0)])}var i=a("47e2"),s=a("6c02"),u=a("8552"),p=a("b356"),d=(a("3b18"),a("f64c")),b=(a("cd17"),a("ed3b")),m=a("e723"),O=a("d257"),j=Object(o["defineComponent"])({__name:"ScaleOut",props:{groupRecord:null},emits:["cancel","refresh"],setup(e,{emit:t}){var a;const c=e,n=Object(o["ref"])(!1),r=Object(o["reactive"])(Object(u["a"])()),l=Object(o["ref"])(),i=Object(o["reactive"])({resourceGroup:(null===(a=c.groupRecord)||void 0===a?void 0:a.name)||"",parallelism:1});function s(){l.value.validateFields().then(async()=>{n.value=!0,await Object(m["i"])({optimizerGroup:i.resourceGroup||"",parallelism:Number(i.parallelism)}),l.value.resetFields(),t("cancel"),t("refresh"),n.value=!1}).catch(()=>{n.value=!1})}function p(){l.value.resetFields(),t("cancel")}return Object(o["onMounted"])(()=>{}),(e,t)=>{const a=Object(o["resolveComponent"])("a-form-item"),c=Object(o["resolveComponent"])("a-input"),u=Object(o["resolveComponent"])("a-form"),d=Object(o["resolveComponent"])("a-modal");return Object(o["openBlock"])(),Object(o["createBlock"])(d,{visible:!0,title:e.$t("scaleOut"),confirmLoading:n.value,closable:!1,onOk:s,onCancel:p},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(u,{ref_key:"formRef",ref:l,model:i,class:"label-120"},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(a,{name:"resourceGroup",label:e.$t("resourceGroup")},{default:Object(o["withCtx"])(()=>[Object(o["createTextVNode"])(Object(o["toDisplayString"])(i.resourceGroup),1)]),_:1},8,["label"]),Object(o["createVNode"])(a,{name:"parallelism",label:e.$t("parallelism"),rules:[{required:!0,message:""+r.parallelismPh}]},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(c,{value:i.parallelism,"onUpdate:value":t[0]||(t[0]=e=>i.parallelism=e),type:"number",placeholder:r.parallelismPh},null,8,["value","placeholder"])]),_:1},8,["label","rules"])]),_:1},8,["model"])]),_:1},8,["title","confirmLoading"])}}});const f=j;var v=f;const h={class:"list-wrap"},g=["title"],y=["onClick"],k=["onClick"],C=["onClick"],z=["onClick"];var w=Object(o["defineComponent"])({__name:"List",props:{curGroupName:null,type:null},emits:["editGroup","refresh"],setup(e,{emit:t}){const a=e,{t:c}=Object(i["b"])(),n=(Object(s["e"])(),Object(o["shallowReactive"])({pending:{title:"pending",color:"#ffcc00"},planning:{title:"planning",color:"#076de3"},idle:{title:"idle",color:"#c9cdd4"},minor:{title:"minor",color:"#0ad787"},major:{title:"major",color:"#0ad787"},full:{title:"full",color:"#0ad787"},committing:{title:"committing",color:"#0ad787"}})),r=Object(o["ref"])(!1),l=Object(o["ref"])(!1),u=Object(o["shallowReactive"])([{dataIndex:"name",title:c("name"),ellipsis:!0},{dataIndex:"container",title:c("container"),width:"23%",ellipsis:!0},{dataIndex:"resourceOccupation",title:c("resourceOccupation"),width:"23%",ellipsis:!0},{dataIndex:"operationGroup",title:c("operation"),key:"operationGroup",ellipsis:!0,width:230,scopedSlots:{customRender:"operationGroup"}}]),j=Object(o["shallowReactive"])([{dataIndex:"index",title:c("order"),width:80,ellipsis:!0},{dataIndex:"groupName",title:c("optimizerGroup"),ellipsis:!0},{dataIndex:"container",title:c("container"),ellipsis:!0},{dataIndex:"jobStatus",title:c("status"),ellipsis:!0},{dataIndex:"resourceAllocation",title:c("resourceAllocation"),width:"20%",ellipsis:!0},{dataIndex:"operation",title:c("operation"),key:"operation",ellipsis:!0,width:160,scopedSlots:{customRender:"operationGroup"}}]),f=Object(o["reactive"])(Object(p["a"])()),w=Object(o["reactive"])([]),N=Object(o["reactive"])([]),x=Object(o["computed"])(()=>"optimizers"===a.type?j:u),G=Object(o["computed"])(()=>"optimizers"===a.type?w:N);function S(e){e&&(f.current=1),"optimizers"===a.type?B():E()}function V(e){"external"!==e.container&&b["a"].confirm({title:c("releaseOptModalTitle"),content:"",okText:"",cancelText:"",onOk:()=>{P(e)}})}async function P(e){try{l.value=!0,await Object(m["h"])({optimizerGroup:e.groupName,jobId:e.jobId}),S(!0),t("refreshCurGroupInfo")}finally{l.value=!1}}async function B(){try{w.length=0,r.value=!0;const e={optimizerGroup:"all",page:f.current,pageSize:f.pageSize},t=await Object(m["c"])(e),{list:a,total:o}=t;f.total=o,(a||[]).forEach((e,t)=>{e.resourceAllocation=`${e.coreNumber} ${c("core")} ${Object(O["h"])(e.memory)}`,e.index=(f.current-1)*f.pageSize+t+1,w.push(e)})}catch(e){}finally{r.value=!1}}async function E(){try{N.length=0,r.value=!0;const e=await Object(m["e"])();f.total=e.length,(e||[]).forEach(e=>{e.name=e.resourceGroup.name,e.container=e.resourceGroup.container,e.resourceOccupation=`${e.occupationCore} ${c("core")} ${Object(O["h"])(e.occupationMemory)}`,N.push(e)})}catch(e){}finally{r.value=!1}}const I=e=>{t("editGroup",e)},_=async e=>{const t=await Object(m["g"])({name:e.name});t?b["a"].confirm({title:c("deleteGroupModalTitle"),onOk:async()=>{await Object(m["f"])({name:e.name}),d["a"].success(`${c("remove")} ${c("success")}`),S()}}):b["a"].warning({title:c("cannotDeleteGroupModalTitle"),content:c("cannotDeleteGroupModalContent")})},R=Object(o["ref"])({}),$=Object(o["ref"])(!1),D=e=>{"external"!==e.container&&(R.value={...e},$.value=!0)};function T({current:e=f.current,pageSize:t=f.pageSize}){f.current=e;const a=t!==f.pageSize;f.pageSize=t,S(a)}return Object(o["onMounted"])(()=>{S()}),(e,t)=>{const a=Object(o["resolveComponent"])("a-table"),i=Object(o["resolveComponent"])("u-loading");return Object(o["openBlock"])(),Object(o["createElementBlock"])(o["Fragment"],null,[Object(o["createElementVNode"])("div",h,[Object(o["createVNode"])(a,{class:"ant-table-common",columns:Object(o["unref"])(x),"data-source":Object(o["unref"])(G),pagination:f,loading:r.value,onChange:T},{bodyCell:Object(o["withCtx"])(({column:e,record:t})=>["durationDisplay"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])("span",{key:0,title:t.durationDesc},Object(o["toDisplayString"])(t.durationDisplay),9,g)):Object(o["createCommentVNode"])("",!0),"optimizeStatus"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])(o["Fragment"],{key:1},[Object(o["createElementVNode"])("span",{style:Object(o["normalizeStyle"])({"background-color":(Object(o["unref"])(n)[t.optimizeStatus]||{}).color}),class:"status-icon"},null,4),Object(o["createElementVNode"])("span",null,Object(o["toDisplayString"])(t.optimizeStatus),1)],64)):Object(o["createCommentVNode"])("",!0),"operation"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])("span",{key:2,class:Object(o["normalizeClass"])(["primary-link",{disabled:"external"===t.container}]),onClick:e=>V(t)},Object(o["toDisplayString"])(Object(o["unref"])(c)("release")),11,y)):Object(o["createCommentVNode"])("",!0),"operationGroup"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])(o["Fragment"],{key:3},[Object(o["createElementVNode"])("span",{class:Object(o["normalizeClass"])(["primary-link g-mr-12",{disabled:"external"===t.container}]),onClick:e=>D(t)},Object(o["toDisplayString"])(Object(o["unref"])(c)("scaleOut")),11,k),Object(o["createElementVNode"])("span",{class:"primary-link g-mr-12",onClick:e=>I(t)},Object(o["toDisplayString"])(Object(o["unref"])(c)("edit")),9,C),Object(o["createElementVNode"])("span",{class:"primary-link",onClick:e=>_(t)},Object(o["toDisplayString"])(Object(o["unref"])(c)("remove")),9,z)],64)):Object(o["createCommentVNode"])("",!0)]),_:1},8,["columns","data-source","pagination","loading"])]),$.value?(Object(o["openBlock"])(),Object(o["createBlock"])(v,{key:0,groupRecord:R.value,onCancel:t[0]||(t[0]=e=>$.value=!1),onRefresh:S},null,8,["groupRecord"])):Object(o["createCommentVNode"])("",!0),l.value?(Object(o["openBlock"])(),Object(o["createBlock"])(i,{key:1})):Object(o["createCommentVNode"])("",!0)],64)}}}),N=(a("d72a"),a("6b0d")),x=a.n(N);const G=x()(w,[["__scopeId","data-v-52174f9c"]]);var S=G;const V={class:"list-wrap"},P=["title","onClick"],B=["title"],E=["onClick"];var I=Object(o["defineComponent"])({__name:"List",props:{curGroupName:null,type:null},emits:["refreshCurGroupInfo"],setup(e,{emit:t}){const a=e,{t:c}=Object(i["b"])(),n=Object(s["e"])(),r=Object(o["shallowReactive"])({pending:{title:"pending",color:"#ffcc00"},planning:{title:"planning",color:"#076de3"},idle:{title:"idle",color:"#c9cdd4"},minor:{title:"minor",color:"#0ad787"},major:{title:"major",color:"#0ad787"},full:{title:"full",color:"#0ad787"},committing:{title:"committing",color:"#0ad787"}}),l=Object(o["ref"])(!1),u=Object(o["ref"])(!1),d=Object(o["shallowReactive"])([{dataIndex:"tableName",title:c("table"),ellipsis:!0,scopedSlots:{customRender:"tableName"}},{dataIndex:"groupName",title:c("optimizerGroup"),width:"16%",ellipsis:!0},{dataIndex:"optimizeStatus",title:c("optimizingStatus"),width:"16%",ellipsis:!0},{dataIndex:"durationDisplay",title:c("duration"),width:"10%",ellipsis:!0},{dataIndex:"fileCount",title:c("fileCount"),width:"10%",ellipsis:!0},{dataIndex:"fileSizeDesc",title:c("fileSize"),width:"10%",ellipsis:!0},{dataIndex:"quota",title:c("quota"),width:"10%",ellipsis:!0},{dataIndex:"quotaOccupationDesc",title:c("occupation"),width:120,ellipsis:!0}]),j=Object(o["shallowReactive"])([{dataIndex:"index",title:c("order"),width:80,ellipsis:!0},{dataIndex:"groupName",title:c("optimizerGroup"),ellipsis:!0},{dataIndex:"container",title:c("container"),ellipsis:!0},{dataIndex:"jobStatus",title:c("status"),ellipsis:!0},{dataIndex:"resourceAllocation",title:c("resourceAllocation"),width:"20%",ellipsis:!0},{dataIndex:"operation",title:c("operation"),key:"operation",ellipsis:!0,width:160,scopedSlots:{customRender:"operation"}}]),f=Object(o["reactive"])(Object(p["a"])()),v=Object(o["reactive"])([]),h=Object(o["reactive"])([]),g=Object(o["computed"])(()=>"optimizers"===a.type?j:d),y=Object(o["computed"])(()=>"optimizers"===a.type?v:h);function k(e){e&&(f.current=1),"optimizers"===a.type?C():z()}async function C(){try{v.length=0,l.value=!0;const e={optimizerGroup:a.curGroupName,page:f.current,pageSize:f.pageSize},t=await Object(m["c"])(e),{list:o,total:n}=t;f.total=n,(o||[]).forEach((e,t)=>{e.resourceAllocation=`${e.coreNumber} ${c("core")} ${Object(O["h"])(e.memory)}`,e.index=(f.current-1)*f.pageSize+t+1,v.push(e)})}catch(e){}finally{l.value=!1}}async function z(){try{h.length=0,l.value=!0;const e={optimizerGroup:a.curGroupName||"",page:f.current,pageSize:f.pageSize},t=await Object(m["d"])(e),{list:o,total:c}=t;f.total=c,(o||[]).forEach(e=>{e.quotaOccupationDesc=e.quotaOccupation-5e-4>0?(100*e.quotaOccupation).toFixed(1)+"%":"0",e.durationDesc=Object(O["e"])(e.duration||0),e.durationDisplay=Object(O["d"])(e.duration||0),e.fileSizeDesc=Object(O["a"])(e.fileSize),h.push(e)})}catch(e){}finally{l.value=!1}}function w(e){"external"!==e.container&&b["a"].confirm({title:c("releaseOptModalTitle"),content:"",okText:"",cancelText:"",onOk:()=>{N(e)}})}async function N(e){try{u.value=!0,await Object(m["h"])({optimizerGroup:e.groupName,jobId:e.jobId}),k(!0),t("refreshCurGroupInfo")}finally{u.value=!1}}function x({current:e=f.current,pageSize:t=f.pageSize}){f.current=e;const a=t!==f.pageSize;f.pageSize=t,k(a)}function G(e){const{catalog:t,database:a,tableName:o}=e.tableIdentifier;n.push({path:"/tables",query:{catalog:t,db:a,table:o}})}return Object(o["watch"])(()=>a.curGroupName,e=>{e&&k()}),Object(o["onMounted"])(()=>{k()}),(e,t)=>{const a=Object(o["resolveComponent"])("a-table"),n=Object(o["resolveComponent"])("u-loading");return Object(o["openBlock"])(),Object(o["createElementBlock"])(o["Fragment"],null,[Object(o["createElementVNode"])("div",V,[Object(o["createVNode"])(a,{class:"ant-table-common",columns:Object(o["unref"])(g),"data-source":Object(o["unref"])(y),pagination:f,loading:l.value,onChange:x},{bodyCell:Object(o["withCtx"])(({column:e,record:t})=>["tableName"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])("span",{key:0,title:t.tableName,class:"primary-link",onClick:e=>G(t)},Object(o["toDisplayString"])(t.tableName),9,P)):Object(o["createCommentVNode"])("",!0),"durationDisplay"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])("span",{key:1,title:t.durationDesc},Object(o["toDisplayString"])(t.durationDisplay),9,B)):Object(o["createCommentVNode"])("",!0),"optimizeStatus"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])(o["Fragment"],{key:2},[Object(o["createElementVNode"])("span",{style:Object(o["normalizeStyle"])({"background-color":(Object(o["unref"])(r)[t.optimizeStatus]||{}).color}),class:"status-icon"},null,4),Object(o["createElementVNode"])("span",null,Object(o["toDisplayString"])(t.optimizeStatus),1)],64)):Object(o["createCommentVNode"])("",!0),"operation"===e.dataIndex?(Object(o["openBlock"])(),Object(o["createElementBlock"])("span",{key:3,class:Object(o["normalizeClass"])(["primary-link",{disabled:"external"===t.container}]),onClick:e=>w(t)},Object(o["toDisplayString"])(Object(o["unref"])(c)("release")),11,E)):Object(o["createCommentVNode"])("",!0)]),_:1},8,["columns","data-source","pagination","loading"])]),u.value?(Object(o["openBlock"])(),Object(o["createBlock"])(n,{key:0})):Object(o["createCommentVNode"])("",!0)],64)}}});a("2d24");const _=x()(I,[["__scopeId","data-v-7dc2a88d"]]);var R=_,$=a("4e01"),D=Object(o["defineComponent"])({__name:"GroupModal",props:{edit:{type:Boolean},editRecord:null},emits:["cancel","refresh"],setup(e,{emit:t}){const a=e,{t:c}=Object(i["b"])(),n=Object(o["reactive"])(Object(u["a"])()),r=Object(o["ref"])({containerList:[]});async function l(){const e=await Object(m["b"])(),t=(e||[]).map(e=>({label:e,value:e}));r.value.containerList=t}const s=Object(o["reactive"])({name:"",container:void 0,properties:{}}),p=Object(o["ref"])(!1),b=()=>{t("cancel")},O=Object(o["ref"])(),j=Object(o["ref"])(),f=()=>{O.value.validateFields().then(async()=>{try{const e=await j.value.getProperties(),o={name:s.name,container:s.container,properties:e};a.edit?await Object(m["j"])(o):await Object(m["a"])(o),d["a"].success(`${c("save")} ${c("success")}`),t("refresh")}catch(e){d["a"].error(`${c("save")} ${c("failed")}`)}})};return Object(o["onMounted"])(()=>{var e,t,o;(l(),a.edit)&&(s.name=null===(e=a.editRecord)||void 0===e?void 0:e.name,s.container=null===(t=a.editRecord)||void 0===t?void 0:t.container,s.properties=null===(o=a.editRecord)||void 0===o?void 0:o.resourceGroup.properties)}),(t,a)=>{const c=Object(o["resolveComponent"])("a-input"),l=Object(o["resolveComponent"])("a-form-item"),i=Object(o["resolveComponent"])("a-select"),u=Object(o["resolveComponent"])("a-form"),d=Object(o["resolveComponent"])("a-modal");return Object(o["openBlock"])(),Object(o["createBlock"])(d,{visible:!0,title:e.edit?t.$t("editgroup"):t.$t("addgroup"),confirmLoading:p.value,closable:!1,class:"group-modal",onOk:f,onCancel:b},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(u,{ref_key:"formRef",ref:O,model:s,class:"label-120"},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(l,{name:"name",label:t.$t("name"),rules:[{required:!0,message:""+n.groupNamePh}]},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(c,{value:s.name,"onUpdate:value":a[0]||(a[0]=e=>s.name=e),placeholder:n.groupNamePh,disabled:e.edit},null,8,["value","placeholder","disabled"])]),_:1},8,["label","rules"]),Object(o["createVNode"])(l,{name:"container",label:t.$t("container"),rules:[{required:!0,message:""+n.groupContainer}]},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(i,{value:s.container,"onUpdate:value":a[1]||(a[1]=e=>s.container=e),showSearch:!0,options:r.value.containerList,placeholder:n.groupContainer},null,8,["value","options","placeholder"])]),_:1},8,["label","rules"]),Object(o["createVNode"])(l,{label:t.$t("properties")},null,8,["label"]),Object(o["createVNode"])(l,null,{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])($["a"],{propertiesObj:s.properties,isEdit:!0,ref_key:"propertiesRef",ref:j},null,8,["propertiesObj"])]),_:1})]),_:1},8,["model"])]),_:1},8,["title","confirmLoading"])}}});a("5548");const T=D;var M=T,q=Object(o["defineComponent"])({name:"Resource",components:{List:S,GroupModal:M,TableList:R},setup(){const{t:e}=Object(i["b"])(),t=Object(s["e"])(),a=Object(s["d"])(),c=Object(o["shallowReactive"])([{label:e("optimizergroup"),value:"optimizergroup"},{label:e("optimizers"),value:"optimizers"}]),n=Object(o["reactive"])(Object(u["a"])()),r=Object(o["reactive"])(Object(p["a"])()),l=Object(o["reactive"])({activeTab:"optimizergroup",showGroupModal:!1,groupEdit:!1,groupEditRecord:{},groupKeyCount:1,showTab:!1});Object(o["watch"])(()=>a.query,e=>{l.activeTab=e.tab||"tables"},{immediate:!0});const d=e=>{e?(l.groupEdit=!0,l.groupEditRecord={...e}):l.groupEdit=!1,l.showGroupModal=!0},b=e=>{const o={...a.query};o.tab=e,t.replace({query:{...o}})};return Object(o["onMounted"])(()=>{l.showTab=!0}),{placeholder:n,pagination:r,...Object(o["toRefs"])(l),tabConfig:c,onChangeTab:b,editGroup:d,t:e}}});a("d72c");const L=x()(q,[["render",l],["__scopeId","data-v-1b80e946"]]);t["default"]=L},"2d24":function(e,t,a){"use strict";a("d33f")},3335:function(e,t,a){},"4e01":function(e,t,a){"use strict";var o=a("7a23"),c=a("a878"),n=a("d257"),r=a("8552"),l=a("47e2");const i={class:"config-properties"},s={key:0},u={class:"config-header g-flex"},p={class:"td g-flex-ac"},d={class:"td g-flex-ac bd-left"},b=Object(o["createTextVNode"])("+");var m=Object(o["defineComponent"])({__name:"Properties",props:{propertiesObj:null,isEdit:{type:Boolean}},setup(e,{expose:t}){const a=e,{t:m}=Object(l["b"])(),O=Object(o["shallowReactive"])([{dataIndex:"key",title:m("key"),width:284,ellipsis:!0},{dataIndex:"value",title:m("value"),ellipsis:!0}]),j=Object(o["ref"])(),f=Object(o["reactive"])({data:[]}),v=Object(o["reactive"])(Object(r["a"])()),h=Object(o["computed"])(()=>a.isEdit);function g(){f.data.length=0,Object.keys(a.propertiesObj).forEach(e=>{f.data.push({key:e,value:a.propertiesObj[e],uuid:Object(n["g"])()})})}function y(e){const t=f.data.indexOf(e);-1!==t&&f.data.splice(t,1)}function k(){f.data.push({key:"",value:"",uuid:Object(n["g"])()})}return Object(o["watch"])(()=>a.propertiesObj,()=>{g()},{immediate:!0,deep:!0}),t({getPropertiesWithoputValidation(){const e={};return f.data.forEach(t=>{e[t.key]=t.value}),Promise.resolve(e)},getProperties(){return j.value.validateFields().then(()=>{const e={};return f.data.forEach(t=>{e[t.key]=t.value}),Promise.resolve(e)}).catch(()=>!1)}}),Object(o["onMounted"])(()=>{}),(e,t)=>{const a=Object(o["resolveComponent"])("a-input"),n=Object(o["resolveComponent"])("a-form-item"),r=Object(o["resolveComponent"])("a-form"),l=Object(o["resolveComponent"])("a-button"),m=Object(o["resolveComponent"])("a-table");return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",i,[Object(o["unref"])(h)?(Object(o["openBlock"])(),Object(o["createElementBlock"])("div",s,[Object(o["createElementVNode"])("div",u,[Object(o["createElementVNode"])("div",p,Object(o["toDisplayString"])(e.$t("key")),1),Object(o["createElementVNode"])("div",d,Object(o["toDisplayString"])(e.$t("value")),1)]),Object(o["createVNode"])(r,{ref_key:"propertiesFormRef",ref:j,model:f,class:"g-mt-12"},{default:Object(o["withCtx"])(()=>[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(f.data,(t,r)=>(Object(o["openBlock"])(),Object(o["createElementBlock"])("div",{class:"config-row",key:t.uuid},[Object(o["createVNode"])(n,{name:["data",r,"key"],rules:[{required:!0,message:""+e.$t(v.inputPh)}],class:"g-mr-8"},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(a,{value:t.key,"onUpdate:value":e=>t.key=e,style:{width:"100%"}},null,8,["value","onUpdate:value"])]),_:2},1032,["name","rules"]),Object(o["createVNode"])(n,{name:["data",r,"value"],rules:[{required:!0,message:""+e.$t(v.inputPh)}]},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(a,{value:t.value,"onUpdate:value":e=>t.value=e,style:{width:"100%"}},null,8,["value","onUpdate:value"])]),_:2},1032,["name","rules"]),Object(o["createVNode"])(Object(o["unref"])(c["a"]),{class:"icon-close",onClick:e=>y(t)},null,8,["onClick"])]))),128))]),_:1},8,["model"]),Object(o["createVNode"])(l,{class:"config-btn",onClick:k},{default:Object(o["withCtx"])(()=>[b]),_:1})])):Object(o["createCommentVNode"])("",!0),Object(o["unref"])(h)?Object(o["createCommentVNode"])("",!0):(Object(o["openBlock"])(),Object(o["createBlock"])(m,{key:1,rowKey:"uuid",columns:Object(o["unref"])(O),"data-source":f.data,pagination:!1},null,8,["columns","data-source"]))])}}});a("9054");const O=m;t["a"]=O},"535c":function(e,t,a){},5548:function(e,t,a){"use strict";a("3335")},"6c78":function(e,t,a){},8552:function(e,t,a){"use strict";a.d(t,"a",(function(){return n}));var o=a("7a23"),c=a("47e2");function n(){const{t:e}=Object(c["b"])(),t=Object(o["computed"])(()=>e("catalog")).value,a=Object(o["computed"])(()=>e("databaseName")).value,n=Object(o["computed"])(()=>e("tableName")).value,r=Object(o["computed"])(()=>e("optimzerGroup")).value,l=Object(o["computed"])(()=>e("resourceGroup")).value,i=Object(o["computed"])(()=>e("parallelism")).value,s=Object(o["computed"])(()=>e("username")).value,u=Object(o["computed"])(()=>e("password")).value,p=Object(o["computed"])(()=>e("database",2)).value,d=Object(o["computed"])(()=>e("table",2)).value,b=Object(o["computed"])(()=>e("name")).value,m=Object(o["computed"])(()=>e("container")).value;return{selectPh:e("selectPlaceholder"),inputPh:e("inputPlaceholder"),selectClPh:e("selectPlaceholder",{selectPh:t}),selectDBPh:e("selectPlaceholder",{selectPh:a}),inputDBPh:e("inputPlaceholder",{inputPh:a}),inputClPh:e("inputPlaceholder",{inputPh:t}),inputTNPh:e("inputPlaceholder",{inputPh:n}),selectOptGroupPh:e("inputPlaceholder",{inputPh:r}),resourceGroupPh:e("inputPlaceholder",{inputPh:l}),parallelismPh:e("inputPlaceholder",{inputPh:i}),usernamePh:e("inputPlaceholder",{inputPh:s}),passwordPh:e("inputPlaceholder",{inputPh:u}),filterDBPh:e("filterPlaceholder",{inputPh:p}),filterTablePh:e("filterPlaceholder",{inputPh:d}),groupNamePh:e("inputPlaceholder",{inputPh:b}),groupContainer:e("selectPlaceholder",{selectPh:m})}}},9054:function(e,t,a){"use strict";a("6c78")},b356:function(e,t,a){"use strict";function o(){const e=0,t=1,a=["25","50","100"],o=25;return{total:e,current:t,pageSize:o,pageSizeOptions:a,showQuickJumper:!0,showSizeChanger:!0,hideOnSinglePage:!1}}a.d(t,"a",(function(){return o}))},cbb2:function(e,t,a){},d33f:function(e,t,a){},d72a:function(e,t,a){"use strict";a("535c")},d72c:function(e,t,a){"use strict";a("cbb2")},e723:function(e,t,a){"use strict";a.d(t,"d",(function(){return c})),a.d(t,"c",(function(){return n})),a.d(t,"i",(function(){return r})),a.d(t,"h",(function(){return l})),a.d(t,"e",(function(){return i})),a.d(t,"b",(function(){return s})),a.d(t,"a",(function(){return u})),a.d(t,"j",(function(){return p})),a.d(t,"g",(function(){return d})),a.d(t,"f",(function(){return b}));var o=a("b32d");function c(e){const{optimizerGroup:t,page:a,pageSize:c}=e;return o["a"].get(`ams/v1/optimize/optimizerGroups/${t}/tables`,{params:{page:a,pageSize:c}})}function n(e){const{optimizerGroup:t,page:a,pageSize:c}=e;return o["a"].get(`ams/v1/optimize/optimizerGroups/${t}/optimizers`,{params:{page:a,pageSize:c}})}function r(e){const{optimizerGroup:t,parallelism:a}=e;return o["a"].post(`ams/v1/optimize/optimizerGroups/${t}/optimizers`,{parallelism:a})}function l(e){const{optimizerGroup:t,jobId:a}=e;return o["a"].delete(`ams/v1/optimize/optimizerGroups/${t}/optimizers/${a}`)}async function i(){const e=await o["a"].get("ams/v1/optimize/resourceGroups");return e}const s=async()=>{const e=await o["a"].get("ams/v1/optimize/containers/get");return e},u=e=>o["a"].post("ams/v1/optimize/resourceGroups",e),p=e=>o["a"].put("ams/v1/optimize/resourceGroups",e),d=e=>o["a"].get(`/ams/v1/optimize/resourceGroups/${e.name}/delete/check`),b=e=>o["a"].delete("/ams/v1/optimize/resourceGroups/"+e.name)}}]);