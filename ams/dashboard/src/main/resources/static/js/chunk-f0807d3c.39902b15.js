(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f0807d3c"],{"0014":function(e,t,a){},5738:function(e,t,a){"use strict";a.d(t,"b",(function(){return c})),a.d(t,"c",(function(){return l})),a.d(t,"l",(function(){return o})),a.d(t,"k",(function(){return s})),a.d(t,"e",(function(){return r})),a.d(t,"p",(function(){return i})),a.d(t,"i",(function(){return u})),a.d(t,"h",(function(){return d})),a.d(t,"j",(function(){return b})),a.d(t,"d",(function(){return p})),a.d(t,"f",(function(){return m})),a.d(t,"g",(function(){return g})),a.d(t,"n",(function(){return O})),a.d(t,"q",(function(){return j})),a.d(t,"o",(function(){return v})),a.d(t,"a",(function(){return f})),a.d(t,"m",(function(){return h}));var n=a("b32d");function c(){return n["a"].get("ams/v1/catalogs")}function l(e){const{catalog:t,keywords:a}=e;return n["a"].get(`ams/v1/catalogs/${t}/databases`,{params:{keywords:a}})}function o(e){const{catalog:t,db:a,keywords:c}=e;return n["a"].get(`ams/v1/catalogs/${t}/databases/${a}/tables`,{params:{keywords:c}})}function s({catalog:e="",db:t="",table:a="",token:c=""}){return n["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/details`,{params:{token:c}})}function r({catalog:e="",db:t="",table:a=""}){return n["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/hive/details`)}function i({catalog:e="",db:t="",table:a=""}){return n["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/upgrade/status`)}function u(e){const{catalog:t,db:a,table:c,page:l,pageSize:o,token:s}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/partitions`,{params:{page:l,pageSize:o,token:s}})}function d(e){const{catalog:t,db:a,table:c,partition:l,page:o,pageSize:s,token:r}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/partitions/${l}/files`,{params:{page:o,pageSize:s,token:r}})}function b(e){const{catalog:t,db:a,table:c,page:l,pageSize:o,token:s,ref:r,operation:i}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/snapshots`,{params:{page:l,pageSize:o,token:s,ref:r,operation:i}})}function p(e){const{catalog:t,db:a,table:c,snapshotId:l,page:o,pageSize:s,token:r}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/snapshots/${l}/detail`,{params:{page:o,pageSize:s,token:r}})}function m(e){const{catalog:t,db:a,table:c,page:l,pageSize:o,token:s}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/operations`,{params:{page:l,pageSize:o,token:s}})}function g(e){const{catalog:t,db:a,table:c,page:l,pageSize:o,token:s}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/optimizing-processes`,{params:{page:l,pageSize:o,token:s}})}function O(e){const{catalog:t,db:a,table:c,processId:l,page:o,pageSize:s,token:r}=e;return n["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/optimizing-processes/${l}/tasks`,{params:{page:o,pageSize:s,token:r}})}function j({catalog:e="",db:t="",table:a="",properties:c={},pkList:l=[]}){return n["a"].post(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/upgrade`,{properties:c,pkList:l})}function v(){return n["a"].get("ams/v1/upgrade/properties")}function f(e){const{catalog:t,db:a,table:c}=e;return n["a"].get(`/ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/branches`)}function h(e){const{catalog:t,db:a,table:c}=e;return n["a"].get(`/ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/tags`)}},8552:function(e,t,a){"use strict";a.d(t,"a",(function(){return l}));var n=a("7a23"),c=a("47e2");function l(){const{t:e}=Object(c["b"])(),t=Object(n["computed"])(()=>e("catalog")).value,a=Object(n["computed"])(()=>e("databaseName")).value,l=Object(n["computed"])(()=>e("tableName")).value,o=Object(n["computed"])(()=>e("optimzerGroup")).value,s=Object(n["computed"])(()=>e("resourceGroup")).value,r=Object(n["computed"])(()=>e("parallelism")).value,i=Object(n["computed"])(()=>e("username")).value,u=Object(n["computed"])(()=>e("password")).value,d=Object(n["computed"])(()=>e("database",2)).value,b=Object(n["computed"])(()=>e("table",2)).value,p=Object(n["computed"])(()=>e("name")).value,m=Object(n["computed"])(()=>e("container")).value;return{selectPh:e("selectPlaceholder"),inputPh:e("inputPlaceholder"),selectClPh:e("selectPlaceholder",{selectPh:t}),selectDBPh:e("selectPlaceholder",{selectPh:a}),inputDBPh:e("inputPlaceholder",{inputPh:a}),inputClPh:e("inputPlaceholder",{inputPh:t}),inputTNPh:e("inputPlaceholder",{inputPh:l}),selectOptGroupPh:e("inputPlaceholder",{inputPh:o}),resourceGroupPh:e("inputPlaceholder",{inputPh:s}),parallelismPh:e("inputPlaceholder",{inputPh:r}),usernamePh:e("inputPlaceholder",{inputPh:i}),passwordPh:e("inputPlaceholder",{inputPh:u}),filterDBPh:e("filterPlaceholder",{inputPh:d}),filterTablePh:e("filterPlaceholder",{inputPh:b}),groupNamePh:e("inputPlaceholder",{inputPh:p}),groupContainer:e("selectPlaceholder",{selectPh:m})}}},"87d5":function(e,t,a){"use strict";a("974e")},9065:function(e,t,a){"use strict";a("0014")},"974e":function(e,t,a){},b0b0:function(e,t,a){"use strict";a("d9da")},d323:function(e,t,a){"use strict";a.r(t);var n=a("7a23");const c={class:"console-wrap"},l={class:"sql-block"},o={class:"top-ops g-flex-jsb"},s={class:"title-left g-flex-ac"},r={class:"select-catalog g-mr-12"},i={class:"label"},u={class:"title-right"},d={class:"sql-content"},b={class:"sql-raw"},p={class:"g-ml-12"},m={class:"sql-shortcuts"},g={class:"shortcuts"},O={class:"tab-operation"},j={class:"tab"},v=["onClick"],f={class:"debug-result"};function h(e,t,a,h,k,y){const C=Object(n["resolveComponent"])("a-select"),B=Object(n["resolveComponent"])("svg-icon"),N=Object(n["resolveComponent"])("a-tooltip"),S=Object(n["resolveComponent"])("sql-editor"),E=Object(n["resolveComponent"])("loading-outlined"),w=Object(n["resolveComponent"])("close-circle-outlined"),$=Object(n["resolveComponent"])("check-circle-outlined"),V=Object(n["resolveComponent"])("a-button"),P=Object(n["resolveComponent"])("sql-log"),q=Object(n["resolveComponent"])("sql-result"),L=Object(n["resolveComponent"])("u-loading");return Object(n["openBlock"])(),Object(n["createElementBlock"])("div",c,[Object(n["createElementVNode"])("div",{class:Object(n["normalizeClass"])(["console-content",{fullscreen:e.fullscreen}])},[Object(n["createElementVNode"])("div",{style:Object(n["normalizeStyle"])({height:e.sqlResultHeight+"px"}),class:"sql-wrap"},[Object(n["createElementVNode"])("div",l,[Object(n["createElementVNode"])("div",o,[Object(n["createElementVNode"])("div",s,[Object(n["createElementVNode"])("div",r,[Object(n["createElementVNode"])("span",i,Object(n["toDisplayString"])(e.$t("use")),1),Object(n["createVNode"])(C,{value:e.curCatalog,"onUpdate:value":t[0]||(t[0]=t=>e.curCatalog=t),style:{width:"200px"},options:e.catalogOptions,onChange:e.changeUseCatalog},null,8,["value","options","onChange"])]),"Running"===e.runStatus?(Object(n["openBlock"])(),Object(n["createBlock"])(N,{key:0,title:e.$t("pause"),placement:"bottom"},{default:Object(n["withCtx"])(()=>[Object(n["createVNode"])(B,{className:"icon-svg","icon-class":"sqlpause",onClick:t[1]||(t[1]=t=>e.handleIconClick("pause")),class:"g-mr-12",disabled:e.readOnly},null,8,["disabled"])]),_:1},8,["title"])):(Object(n["openBlock"])(),Object(n["createBlock"])(N,{key:1,title:e.$t("run"),placement:"bottom"},{default:Object(n["withCtx"])(()=>[Object(n["createVNode"])(B,{className:"icon-svg","icon-class":"sqldebug",onClick:t[2]||(t[2]=t=>e.handleIconClick("debug")),class:"g-mr-12",disabled:e.readOnly},null,8,["disabled"])]),_:1},8,["title"])),Object(n["createVNode"])(N,{title:e.$t("format"),placement:"bottom"},{default:Object(n["withCtx"])(()=>[Object(n["createVNode"])(B,{className:"icon-svg",isStroke:!0,"icon-class":"format",onClick:t[3]||(t[3]=t=>e.handleIconClick("format")),disabled:e.readOnly},null,8,["disabled"])]),_:1},8,["title"])]),Object(n["createElementVNode"])("div",u,[Object(n["createVNode"])(N,{title:e.fullscreen?e.$t("recovery"):e.$t("fullscreen"),placement:"bottom",getPopupContainer:e.getPopupContainer},{default:Object(n["withCtx"])(()=>[Object(n["createVNode"])(B,{className:"icon-svg",isStroke:!0,"icon-class":e.fullscreen?"sqlinit":"sqlmax",onClick:e.handleFull,disabled:!1,class:"g-ml-12"},null,8,["icon-class","onClick"])]),_:1},8,["title","getPopupContainer"])])]),Object(n["createElementVNode"])("div",d,[Object(n["createElementVNode"])("div",b,[Object(n["createVNode"])(S,{ref:"sqlEditorRef",sqlValue:e.sqlSource,value:e.sqlSource,"onUpdate:value":t[4]||(t[4]=t=>e.sqlSource=t),readOnly:e.readOnly,options:{readOnly:e.readOnly,minimap:{enabled:!1}}},null,8,["sqlValue","value","readOnly","options"])]),e.runStatus?(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",{key:0,class:"run-status",style:Object(n["normalizeStyle"])({background:e.bgcMap[e.runStatus]})},["Running"===e.runStatus?(Object(n["openBlock"])(),Object(n["createBlock"])(E,{key:0,style:{color:"#1890ff"}})):Object(n["createCommentVNode"])("",!0),"Canceled"===e.runStatus||"Failed"===e.runStatus?(Object(n["openBlock"])(),Object(n["createBlock"])(w,{key:1,style:{color:"#ff4d4f"}})):Object(n["createCommentVNode"])("",!0),"Finished"===e.runStatus?(Object(n["openBlock"])(),Object(n["createBlock"])($,{key:2,style:{color:"#52c41a"}})):Object(n["createCommentVNode"])("",!0),"Created"===e.runStatus?(Object(n["openBlock"])(),Object(n["createBlock"])(w,{key:3,style:{color:"#333"}})):Object(n["createCommentVNode"])("",!0),Object(n["createElementVNode"])("span",p,Object(n["toDisplayString"])(e.$t(e.runStatus)),1)],4)):Object(n["createCommentVNode"])("",!0)])]),Object(n["createElementVNode"])("div",m,[Object(n["createElementVNode"])("div",g,Object(n["toDisplayString"])(e.$t("sqlShortcuts")),1),(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(e.shortcuts,t=>(Object(n["openBlock"])(),Object(n["createBlock"])(V,{key:t,type:"link",disabled:"Running"===e.runStatus,onClick:a=>e.generateCode(t),class:"code"},{default:Object(n["withCtx"])(()=>[Object(n["createTextVNode"])(Object(n["toDisplayString"])(t),1)]),_:2},1032,["disabled","onClick"]))),128))])],4),Object(n["createElementVNode"])("div",{class:Object(n["normalizeClass"])(["sql-result",e.resultFullscreen?"result-full":""]),style:Object(n["normalizeStyle"])({height:`calc(100% - ${e.sqlResultHeight}px)`})},[Object(n["createElementVNode"])("span",{class:"drag-line",onMousedown:t[5]||(t[5]=(...t)=>e.dragMounseDown&&e.dragMounseDown(...t))},[Object(n["createVNode"])(B,{class:"icon","icon-class":"slide"})],32),Object(n["createElementVNode"])("div",O,[Object(n["createElementVNode"])("div",j,[Object(n["createElementVNode"])("span",{class:Object(n["normalizeClass"])([{active:"log"===e.operationActive},"tab-item"]),onClick:t[6]||(t[6]=t=>e.operationActive="log")},Object(n["toDisplayString"])(e.$t("log")),3),(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(e.resultTabList,t=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("span",{key:t.id,class:Object(n["normalizeClass"])([{active:e.operationActive===t.id},"tab-item"]),onClick:a=>e.operationActive=t.id},Object(n["toDisplayString"])(t.id),11,v))),128))])]),Object(n["createElementVNode"])("div",f,[Object(n["withDirectives"])(Object(n["createVNode"])(P,{ref:"sqlLogRef"},null,512),[[n["vShow"],"log"===e.operationActive]]),(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(e.resultTabList,t=>(Object(n["openBlock"])(),Object(n["createElementBlock"])(n["Fragment"],{key:t.id},[e.operationActive===t.id?(Object(n["openBlock"])(),Object(n["createBlock"])(q,{key:0,info:t},null,8,["info"])):Object(n["createCommentVNode"])("",!0)],64))),128))])],6)],2),e.loading?(Object(n["openBlock"])(),Object(n["createBlock"])(L,{key:0})):Object(n["createCommentVNode"])("",!0)])}a("3b18");var k=a("f64c"),y=a("c2c6");const C={theme:"arcticSql",language:"sql",fontSize:12,lineHeight:24,fontFamily:'Monaco, Menlo, "Ubuntu Mono", Consolas, source-code-pro, monospace',folding:!0,suggestLineHeight:20,autoIndent:!0,renderLineHighlight:"all",scrollBeyondLastLine:!1,contextmenu:!1,readOnly:!0,fixedOverflowWidgets:!0},B=Object.assign({},C,{theme:"arcticSql",language:"sql",readOnly:!1,lineHeight:24,fontSize:12,fontFamily:'Monaco, Menlo, "Ubuntu Mono", Consolas, source-code-pro, monospace',lineNumbersMinChars:3,wordWrap:"on",renderLineHighlight:"all",minimap:{enabled:!1},contextmenu:!1,automaticLayout:!0,scrollBeyondLastLine:!1});var N=Object(n["defineComponent"])({__name:"index",props:{sqlValue:null,options:null,readOnly:{type:Boolean}},emits:["save","update:value","change"],setup(e,{expose:t,emit:a}){const c=e;let l,o="";const s={};function r(){l&&l.layout()}function i(){if(l){const e=l.addCommand(y["KeyMod"].CtrlCmd|y["KeyCode"].KEY_S,()=>{a("save")});s.save=e;const t=l.addCommand(y["KeyMod"].Alt|y["KeyMod"].Shift|y["KeyCode"].KEY_F,()=>{u()});s.format=t}}function u(){const e=l&&l.getAction("editor.action.formatDocument");e&&e.run()}return Object(n["watch"])(()=>c.sqlValue,e=>{e&&o!==e&&l&&l.setValue(e)}),window.addEventListener("resize",r),t({executeCommand(e){const t=s[e],a=l;t&&a&&a._commandService.executeCommand(t)},updateOptions(e={}){l&&l.updateOptions(e)}}),Object(n["onBeforeUnmount"])(()=>{window.removeEventListener("resize",r),l&&l.dispose()}),Object(n["onMounted"])(()=>{const e=document.getElementsByClassName("m-sql-editor")[0];Object(n["nextTick"])(()=>{const t=l=y["editor"].create(e,{...B,...c.options});i(),t.setValue(c.sqlValue||""),t.onDidChangeModelContent(e=>{const t=l.getValue();a("update:value",t),a("change",t),o=t})})}),(t,a)=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",{class:Object(n["normalizeClass"])(["m-sql-editor",{disabled:e.readOnly}]),style:{height:"100%",width:"100%"}},null,2))}}),S=(a("fea2"),a("6b0d")),E=a.n(S);const w=E()(N,[["__scopeId","data-v-13c652ee"]]);var $=w,V=a("f38b"),P=a("8fe6"),q=a("411c"),L=a("d34f");const z={class:"sql-result-wrap"},I={class:"g-ml-8"},x={key:0,class:"empty"},D={key:1,class:"result-wrap"},M={class:"ant-table sql-result-table",style:{width:"100%"}},F={class:"ant-table-thead"},R={class:"ant-table-tbody"},_=["title"];var H=Object(n["defineComponent"])({__name:"sql-result",props:{info:null},setup(e){const t=e,a=Object(n["computed"])(()=>{var e;return!t.info||!(null!==(e=t.info)&&void 0!==e&&e.columns)}),c=Object(n["computed"])(()=>{var e;return null===(e=t.info)||void 0===e?void 0:e.status});return(e,l)=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",z,[Object(n["createElementVNode"])("div",{class:"result-status",style:Object(n["normalizeStyle"])({background:Object(n["unref"])(V["b"])[Object(n["unref"])(c)]})},["Running"===Object(n["unref"])(c)?(Object(n["openBlock"])(),Object(n["createBlock"])(Object(n["unref"])(P["a"]),{key:0,style:{color:"#1890ff"}})):Object(n["createCommentVNode"])("",!0),"Canceled"===Object(n["unref"])(c)||"Failed"===Object(n["unref"])(c)?(Object(n["openBlock"])(),Object(n["createBlock"])(Object(n["unref"])(q["a"]),{key:1,style:{color:"#ff4d4f"}})):Object(n["createCommentVNode"])("",!0),"Finished"===Object(n["unref"])(c)?(Object(n["openBlock"])(),Object(n["createBlock"])(Object(n["unref"])(L["a"]),{key:2,style:{color:"#52c41a"}})):Object(n["createCommentVNode"])("",!0),"Created"===Object(n["unref"])(c)?(Object(n["openBlock"])(),Object(n["createBlock"])(Object(n["unref"])(q["a"]),{key:3,style:{color:"#333"}})):Object(n["createCommentVNode"])("",!0),Object(n["createElementVNode"])("span",I,Object(n["toDisplayString"])(Object(n["unref"])(c)),1)],4),Object(n["unref"])(a)?(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",x,Object(n["toDisplayString"])(e.$t("noResult")),1)):(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",D,[Object(n["createElementVNode"])("table",M,[Object(n["createElementVNode"])("thead",F,[Object(n["createElementVNode"])("tr",null,[(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(t.info.columns,e=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("th",{key:e},Object(n["toDisplayString"])(e),1))),128))])]),Object(n["createElementVNode"])("tbody",R,[(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(t.info.rowData,(e,t)=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("tr",{key:t+1},[(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(e,(e,a)=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("td",{key:t+e+a},[Object(n["createElementVNode"])("span",{class:"td-val",title:e},Object(n["toDisplayString"])(e),9,_)]))),128))]))),128))])])]))]))}});a("9065");const T=E()(H,[["__scopeId","data-v-5e56b470"]]);var A=T;const G={class:"sql-log"},U=["innerHTML"];var K=Object(n["defineComponent"])({__name:"sql-log",setup(e,{expose:t}){const a=Object(n["ref"])("");return t({initData(e){a.value=e}}),(e,t)=>(Object(n["openBlock"])(),Object(n["createElementBlock"])("div",G,[Object(n["createElementVNode"])("div",{innerHTML:a.value,style:{"white-space":"pre-wrap","font-size":"12px"}},null,8,U)]))}});a("b0b0");const Y=E()(K,[["__scopeId","data-v-d5f228ba"]]);var J=Y,W=a("b32d");function Z(e){return W["a"].get(`ams/v1/terminal/${e}/result`)}function Q(){return W["a"].get("ams/v1/terminal/examples")}function X(e){return W["a"].get("ams/v1/terminal/examples/"+e)}function ee(e){const{catalog:t,sql:a}=e;return W["a"].post(`ams/v1/terminal/catalogs/${t}/execute`,{sql:a})}function te(e){return W["a"].put(`ams/v1/terminal/${e}/stop`)}function ae(e){return W["a"].get(`ams/v1/terminal/${e}/logs`)}function ne(){return W["a"].get("ams/v1/terminal/latestInfos")}var ce=a("5738"),le=a("8552"),oe=Object(n["defineComponent"])({name:"Terminal",components:{SqlEditor:$,SqlResult:A,SqlLog:J,CheckCircleOutlined:L["a"],CloseCircleOutlined:q["a"],LoadingOutlined:P["a"]},setup(){const e=Object(n["reactive"])(Object(le["a"])()),t=Object(n["ref"])(!1),a=Object(n["ref"])(null),c=Object(n["ref"])(null),l=Object(n["ref"])(!1),o=Object(n["ref"])(""),s=Object(n["ref"])(!1),r=Object(n["ref"])(""),i=Object(n["ref"])(),u=Object(n["ref"])(!1),d=Object(n["ref"])(!1),b=Object(n["ref"])("log"),p=Object(n["reactive"])([]),m=Object(n["reactive"])([]),g=Object(n["ref"])(""),O=Object(n["ref"])(),j=Object(n["reactive"])([]),v=Object(n["ref"])(476),f=Object(n["shallowReactive"])(V["b"]),h="easylake-sql-source",y="easylake-use-catalog";Object(n["watch"])(()=>l,()=>{a.value.updateOptions({readOnly:l})});const C=e=>{"debug"!==e?"format"!==e?"pause"===e&&q():a.value&&a.value.executeCommand("format"):P()},B=async()=>{const e=await Object(ce["b"])();if((e||[]).forEach(e=>{p.push({value:e.catalogName,label:e.catalogName})}),p.length){const e=G(y),t=p.findIndex(t=>t.value===e);g.value=t>-1?e:p[0].value}},N=async()=>{const e=await Q();m.push(...e||[])},S=()=>{A(y,g.value)},E=()=>{u.value=!u.value},w=()=>{d.value=!d.value},$=()=>{j.length=0,c.value.initData("")},P=async()=>{try{if(!g.value)return void k["a"].error(e.selectClPh);s.value=!0,$(),r.value="Running";const t=await ee({catalog:g.value,sql:o.value});i.value=t.sessionId||0,z()}catch(t){r.value="Failed",k["a"].error(t.message||"error")}},q=async()=>{if(i.value)try{r.value="Canceled",await te(i.value)}catch(e){r.value="Failed"}},L=async()=>{try{j.length=0;const e=await Z(i.value||0);e&&e.length&&j.push(...e)}catch(e){}},z=async()=>{if(O.value&&clearTimeout(O.value),"Running"===r.value&&i.value){const e=await ae(i.value);b.value="log";const{logStatus:t,logs:a}=e||{};null!==a&&void 0!==a&&a.length&&c.value.initData(a.join("\n")),r.value=t,await L(),"Finished"===t||"Canceled"===t?j.length&&(b.value=j[0].id):O.value=setTimeout(()=>{z()},1500)}},I=async e=>{try{if(b.value="log","Running"===r.value)return;clearTimeout(O.value),t.value=!0;const a=await X(e);o.value=a,s.value=!1,r.value="",$()}catch(a){k["a"].error(a.message)}finally{t.value=!1}},x=()=>document.body,D=async()=>{try{a.value&&(o.value=G(h)),t.value=!0;const e=await ne();i.value=e.sessionId,e.sessionId>0&&(a.value&&!o.value&&(o.value=e.sql||""),r.value="Running",s.value=!0,z())}catch(e){k["a"].error(e.message)}finally{t.value=!1}},M={topbarHeight:48,optionHeight:44,resultTabHeight:40,runStatusHeight:32,gap:48};let F=0,R=0;const _=e=>{F=e.clientY,R=v.value,window.addEventListener("mousemove",H),window.addEventListener("mouseup",T)},H=e=>{const t=e.clientY,a=t-F,n=u.value?0:M.topbarHeight,c=r.value?M.runStatusHeight:0;let l=R+a;l=Math.max(l,M.optionHeight+c),l=Math.min(l,window.innerHeight-n-(u.value?0:M.gap)-M.optionHeight-4),v.value=l},T=()=>{window.removeEventListener("mousemove",H),window.removeEventListener("mouseup",T)},A=(e,t)=>{localStorage.setItem(e,t)},G=e=>localStorage.getItem(e)||"";return Object(n["onBeforeUnmount"])(()=>{clearTimeout(O.value),A(h,o.value)}),Object(n["onMounted"])(()=>{D(),N(),B()}),{loading:t,bgcMap:f,sqlLogRef:c,sqlEditorRef:a,fullscreen:u,resultFullscreen:d,operationActive:b,resultTabList:j,runStatus:r,shortcuts:m,curCatalog:g,catalogOptions:p,handleIconClick:C,handleFull:E,resultFull:w,showDebug:s,sqlSource:o,readOnly:l,generateCode:I,getPopupContainer:x,sqlResultHeight:v,dragMounseDown:_,changeUseCatalog:S}}});a("87d5");const se=E()(oe,[["render",h],["__scopeId","data-v-615f52c8"]]);t["default"]=se},d9da:function(e,t,a){},ec5b:function(e,t,a){},f38b:function(e,t,a){"use strict";var n,c,l,o,s;a.d(t,"b",(function(){return n})),a.d(t,"e",(function(){return c})),a.d(t,"d",(function(){return l})),a.d(t,"a",(function(){return o})),a.d(t,"c",(function(){return s})),function(e){e["Created"]="#f5f5f5",e["Failed"]="#fff2f0",e["Finished"]="#f6ffed",e["Canceled"]="#f5f5f5"}(n||(n={})),function(e){e["failed"]="FAILED",e["upgrading"]="UPGRADING",e["success"]="SUCCESS",e["none"]="NONE"}(c||(c={})),function(e){e["ICEBERG"]="iceberg",e["ARCTIC"]="amoro",e["HIVE"]="hive",e["PAIMON"]="paimon"}(l||(l={})),function(e){e["BRANCH"]="branch",e["TAG"]="tag"}(o||(o={})),function(e){e["ALL"]="all",e["OPTIMIZING"]="optimizing",e["NONOPTIMIZING"]="non-optimizing"}(s||(s={}))},fea2:function(e,t,a){"use strict";a("ec5b")}}]);