(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9fc4147c"],{"376e":function(e,t,a){"use strict";a("5f36")},"3c6b":function(e,t,a){"use strict";a("7fad")},"4e01":function(e,t,a){"use strict";var o=a("7a23"),c=a("a878"),l=a("d257"),n=a("8552"),r=a("47e2");const i={class:"config-properties"},s={key:0},u={class:"config-header g-flex"},p={class:"td g-flex-ac"},b={class:"td g-flex-ac bd-left"},d=Object(o["createTextVNode"])("+");var g=Object(o["defineComponent"])({__name:"Properties",props:{propertiesObj:null,isEdit:{type:Boolean}},setup(e,{expose:t}){const a=e,{t:g}=Object(r["b"])(),f=Object(o["shallowReactive"])([{dataIndex:"key",title:g("key"),width:284,ellipsis:!0},{dataIndex:"value",title:g("value"),ellipsis:!0}]),O=Object(o["ref"])(),j=Object(o["reactive"])({data:[]}),m=Object(o["reactive"])(Object(n["a"])()),h=Object(o["computed"])(()=>a.isEdit);function v(){j.data.length=0,Object.keys(a.propertiesObj).forEach(e=>{j.data.push({key:e,value:a.propertiesObj[e],uuid:Object(l["g"])()})})}function k(e){const t=j.data.indexOf(e);-1!==t&&j.data.splice(t,1)}function y(){j.data.push({key:"",value:"",uuid:Object(l["g"])()})}return Object(o["watch"])(()=>a.propertiesObj,()=>{v()},{immediate:!0,deep:!0}),t({getPropertiesWithoputValidation(){const e={};return j.data.forEach(t=>{e[t.key]=t.value}),Promise.resolve(e)},getProperties(){return O.value.validateFields().then(()=>{const e={};return j.data.forEach(t=>{e[t.key]=t.value}),Promise.resolve(e)}).catch(()=>!1)}}),Object(o["onMounted"])(()=>{}),(e,t)=>{const a=Object(o["resolveComponent"])("a-input"),l=Object(o["resolveComponent"])("a-form-item"),n=Object(o["resolveComponent"])("a-form"),r=Object(o["resolveComponent"])("a-button"),g=Object(o["resolveComponent"])("a-table");return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",i,[Object(o["unref"])(h)?(Object(o["openBlock"])(),Object(o["createElementBlock"])("div",s,[Object(o["createElementVNode"])("div",u,[Object(o["createElementVNode"])("div",p,Object(o["toDisplayString"])(e.$t("key")),1),Object(o["createElementVNode"])("div",b,Object(o["toDisplayString"])(e.$t("value")),1)]),Object(o["createVNode"])(n,{ref_key:"propertiesFormRef",ref:O,model:j,class:"g-mt-12"},{default:Object(o["withCtx"])(()=>[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(j.data,(t,n)=>(Object(o["openBlock"])(),Object(o["createElementBlock"])("div",{class:"config-row",key:t.uuid},[Object(o["createVNode"])(l,{name:["data",n,"key"],rules:[{required:!0,message:""+e.$t(m.inputPh)}],class:"g-mr-8"},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(a,{value:t.key,"onUpdate:value":e=>t.key=e,style:{width:"100%"}},null,8,["value","onUpdate:value"])]),_:2},1032,["name","rules"]),Object(o["createVNode"])(l,{name:["data",n,"value"],rules:[{required:!0,message:""+e.$t(m.inputPh)}]},{default:Object(o["withCtx"])(()=>[Object(o["createVNode"])(a,{value:t.value,"onUpdate:value":e=>t.value=e,style:{width:"100%"}},null,8,["value","onUpdate:value"])]),_:2},1032,["name","rules"]),Object(o["createVNode"])(Object(o["unref"])(c["a"]),{class:"icon-close",onClick:e=>k(t)},null,8,["onClick"])]))),128))]),_:1},8,["model"]),Object(o["createVNode"])(r,{class:"config-btn",onClick:y},{default:Object(o["withCtx"])(()=>[d]),_:1})])):Object(o["createCommentVNode"])("",!0),Object(o["unref"])(h)?Object(o["createCommentVNode"])("",!0):(Object(o["openBlock"])(),Object(o["createBlock"])(g,{key:1,rowKey:"uuid",columns:Object(o["unref"])(f),"data-source":j.data,pagination:!1},null,8,["columns","data-source"]))])}}});a("9054");const f=g;t["a"]=f},5606:function(e,t,a){"use strict";a.d(t,"d",(function(){return c})),a.d(t,"c",(function(){return l})),a.d(t,"b",(function(){return n})),a.d(t,"a",(function(){return r})),a.d(t,"g",(function(){return i})),a.d(t,"f",(function(){return s})),a.d(t,"e",(function(){return u}));var o=a("b32d");function c(){return o["a"].get("ams/v1/catalog/metastore/types")}function l(e){return o["a"].get("ams/v1/catalogs/"+e)}function n(e){return o["a"].delete("ams/v1/catalogs/"+e)}function r(e){return o["a"].get(`ams/v1/catalogs/${e}/delete/check`)}function i(e){const{isCreate:t,name:a}=e;return delete e.isCreate,t?o["a"].post("ams/v1/catalogs",{...e}):o["a"].put("ams/v1/catalogs/"+a,{...e})}function s(){return o["a"].get("ams/v1/settings/system")}function u(){return o["a"].get("ams/v1/settings/containers")}},5738:function(e,t,a){"use strict";a.d(t,"a",(function(){return c})),a.d(t,"b",(function(){return l})),a.d(t,"k",(function(){return n})),a.d(t,"j",(function(){return r})),a.d(t,"d",(function(){return i})),a.d(t,"n",(function(){return s})),a.d(t,"h",(function(){return u})),a.d(t,"g",(function(){return p})),a.d(t,"i",(function(){return b})),a.d(t,"c",(function(){return d})),a.d(t,"e",(function(){return g})),a.d(t,"f",(function(){return f})),a.d(t,"l",(function(){return O})),a.d(t,"o",(function(){return j})),a.d(t,"m",(function(){return m}));var o=a("b32d");function c(){return o["a"].get("ams/v1/catalogs")}function l(e){const{catalog:t,keywords:a}=e;return o["a"].get(`ams/v1/catalogs/${t}/databases`,{params:{keywords:a}})}function n(e){const{catalog:t,db:a,keywords:c}=e;return o["a"].get(`ams/v1/catalogs/${t}/databases/${a}/tables`,{params:{keywords:c}})}function r({catalog:e="",db:t="",table:a="",token:c=""}){return o["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/details`,{params:{token:c}})}function i({catalog:e="",db:t="",table:a=""}){return o["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/hive/details`)}function s({catalog:e="",db:t="",table:a=""}){return o["a"].get(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/upgrade/status`)}function u(e){const{catalog:t,db:a,table:c,page:l,pageSize:n,token:r}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/partitions`,{params:{page:l,pageSize:n,token:r}})}function p(e){const{catalog:t,db:a,table:c,partition:l,page:n,pageSize:r,token:i}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/partitions/${l}/files`,{params:{page:n,pageSize:r,token:i}})}function b(e){const{catalog:t,db:a,table:c,page:l,pageSize:n,token:r}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/snapshots`,{params:{page:l,pageSize:n,token:r}})}function d(e){const{catalog:t,db:a,table:c,snapshotId:l,page:n,pageSize:r,token:i}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/snapshots/${l}/detail`,{params:{page:n,pageSize:r,token:i}})}function g(e){const{catalog:t,db:a,table:c,page:l,pageSize:n,token:r}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/operations`,{params:{page:l,pageSize:n,token:r}})}function f(e){const{catalog:t,db:a,table:c,page:l,pageSize:n,token:r}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/optimizing-processes`,{params:{page:l,pageSize:n,token:r}})}function O(e){const{catalog:t,db:a,table:c,processId:l,page:n,pageSize:r,token:i}=e;return o["a"].get(`ams/v1/tables/catalogs/${t}/dbs/${a}/tables/${c}/optimizing-processes/${l}/tasks`,{params:{page:n,pageSize:r,token:i}})}function j({catalog:e="",db:t="",table:a="",properties:c={},pkList:l=[]}){return o["a"].post(`ams/v1/tables/catalogs/${e}/dbs/${t}/tables/${a}/upgrade`,{properties:c,pkList:l})}function m(){return o["a"].get("ams/v1/upgrade/properties")}},"5f36":function(e,t,a){},"6c78":function(e,t,a){},"7fad":function(e,t,a){},8552:function(e,t,a){"use strict";a.d(t,"a",(function(){return l}));var o=a("7a23"),c=a("47e2");function l(){const{t:e}=Object(c["b"])(),t=Object(o["computed"])(()=>e("catalog")).value,a=Object(o["computed"])(()=>e("databaseName")).value,l=Object(o["computed"])(()=>e("tableName")).value,n=Object(o["computed"])(()=>e("optimzerGroup")).value,r=Object(o["computed"])(()=>e("resourceGroup")).value,i=Object(o["computed"])(()=>e("parallelism")).value,s=Object(o["computed"])(()=>e("username")).value,u=Object(o["computed"])(()=>e("password")).value,p=Object(o["computed"])(()=>e("database",2)).value,b=Object(o["computed"])(()=>e("table",2)).value,d=Object(o["computed"])(()=>e("name")).value,g=Object(o["computed"])(()=>e("container")).value;return{selectPh:e("selectPlaceholder"),inputPh:e("inputPlaceholder"),selectClPh:e("selectPlaceholder",{selectPh:t}),selectDBPh:e("selectPlaceholder",{selectPh:a}),inputDBPh:e("inputPlaceholder",{inputPh:a}),inputClPh:e("inputPlaceholder",{inputPh:t}),inputTNPh:e("inputPlaceholder",{inputPh:l}),selectOptGroupPh:e("inputPlaceholder",{inputPh:n}),resourceGroupPh:e("inputPlaceholder",{inputPh:r}),parallelismPh:e("inputPlaceholder",{inputPh:i}),usernamePh:e("inputPlaceholder",{inputPh:s}),passwordPh:e("inputPlaceholder",{inputPh:u}),filterDBPh:e("filterPlaceholder",{inputPh:p}),filterTablePh:e("filterPlaceholder",{inputPh:b}),groupNamePh:e("inputPlaceholder",{inputPh:d}),groupContainer:e("selectPlaceholder",{selectPh:g})}}},9054:function(e,t,a){"use strict";a("6c78")},"9df0":function(e,t,a){},adb5:function(e,t,a){"use strict";a.r(t);a("cd17");var o=a("ed3b"),c=(a("06f4"),a("fc25")),l=a("7a23"),n=a("5738"),r=(a("3b18"),a("f64c")),i=a("5606"),s=a("47e2"),u=a("4e01"),p=a("8552"),b=a("6c02"),d=a("e723");const g={class:"detail-wrap"},f={class:"detail-content-wrap"},O={class:"content-wrap"},j={class:"header"},m={key:1,class:"config-value"},h={key:1},v={key:1},k={key:1},y={class:"header"},C={key:1,class:"config-value"},E={key:1,class:"config-value"},B={key:1,class:"config-value"},N={key:3},w=["onClick"],P={class:"header"},_={key:1,class:"config-value"},$={key:1,class:"config-value"},S={key:1,class:"config-value"},V={key:6},x=["onClick","title"],I={key:1,class:"config-value"},z={key:1,class:"config-value"},D={class:"header"},G={class:"header"},U={key:0,class:"footer-btn"},R={key:1,class:"footer-btn"};var M=Object(l["defineComponent"])({__name:"Detail",props:{isEdit:{type:Boolean}},emits:["updateEdit","updateCatalogs"],setup(e,{emit:t}){const a=e,c={"Internal Catalog":"Internal Catalog","External Catalog":"External Catalog"},{t:n}=Object(s["b"])(),M=Object(b["d"])(),T=Object(l["reactive"])(Object(p["a"])()),L=Object(l["ref"])(""),A=Object(l["computed"])(()=>a.isEdit),F=Object(l["computed"])(()=>"/ams/v1/files"),K=Object(l["computed"])(()=>{var e;const t=((null===(e=M.query)||void 0===e?void 0:e.catalogname)||"").toString();return"new catalog"===decodeURIComponent(t)}),q=Object(l["computed"])(()=>"hive"===oe.catalog.type),H=(Object(l["computed"])(()=>"ams"===oe.catalog.type),Object(l["ref"])(!1)),X=Object(l["ref"])(),J=Object(l["ref"])(),W=Object(l["ref"])(),Z={MIXED_HIVE:"MIXED_HIVE",ICEBERG:"ICEBERG",MIXED_ICEBERG:"MIXED_ICEBERG",PAIMON:"PAIMON"},Q={[Z.ICEBERG]:"Iceberg",[Z.MIXED_HIVE]:"Mixed Hive",[Z.MIXED_ICEBERG]:"Mixed Iceberg",[Z.PAIMON]:"Paimon"},Y={ams:[Z.MIXED_ICEBERG,Z.ICEBERG],hive:[Z.MIXED_HIVE,Z.MIXED_ICEBERG,Z.ICEBERG,Z.PAIMON],hadoop:[Z.MIXED_ICEBERG,Z.ICEBERG,Z.PAIMON],glue:[Z.MIXED_ICEBERG,Z.ICEBERG],custom:[Z.MIXED_ICEBERG,Z.ICEBERG]},ee={"hadoop.core.site":"core-site.xml","hadoop.hdfs.site":"hdfs-site.xml","hive.site":"hive-site.xml"},te={storageConfig:{"hadoop.core.site":"","hadoop.hdfs.site":""},authConfig:{"auth.kerberos.keytab":"","auth.kerberos.krb5":""}},ae=Object(l["ref"])([{label:c["Internal Catalog"],value:c["Internal Catalog"]},{label:c["External Catalog"],value:c["External Catalog"]}]),oe=Object(l["reactive"])({catalog:{name:"",type:"ams",typeshow:c["Internal Catalog"],optimizerGroup:void 0},tableFormat:"",storageConfig:{},authConfig:{},properties:{},tableProperties:{},storageConfigArray:[],authConfigArray:[]}),ce=Object(l["reactive"])([{label:"SIMPLE",value:"SIMPLE"},{label:"KERBEROS",value:"KERBEROS"}]),le=Object(l["reactive"])([{label:"AK/SK",value:"AK/SK"},{label:"CUSTOM",value:"CUSTOM"}]),ne={"hadoop.core.site":"Hadoop core-site","hadoop.hdfs.site":"Hadoop hdfs-site","hive.site":"Hadoop hive-site"},re={"auth.kerberos.keytab":"Kerberos Keytab","auth.kerberos.krb5":"Kerberos Krb5"},ie={ams:["warehouse"],hadoop:["warehouse"],custom:["catalog-impl"],glue:["warehouse","lock-impl","lock.table"],PAIMON:["warehouse"]};Object(l["watch"])(()=>M.query,e=>{e&&pe()},{immediate:!0,deep:!0});const se=Object(l["reactive"])([]),ue=Object(l["ref"])([]);function pe(){fe()}const be=async()=>{const e=await Object(d["e"])(),t=(e||[]).map(e=>({lable:e.resourceGroup.name,value:e.resourceGroup.name}));ue.value=t};async function de(){const e=await Object(i["d"])();(e||[]).forEach(e=>{"ams"!==e.value&&se.push({label:e.display,value:e.value})}),ge()}function ge(){L.value=(se.find(e=>e.value===oe.catalog.type)||{}).label}async function fe(){try{H.value=!0;const{catalogname:e,type:t}=M.query;if(!e)return;if(K.value){oe.catalog.name="",oe.catalog.type=t||"ams",oe.catalog.optimizerGroup=void 0,oe.tableFormat=Z.MIXED_ICEBERG,oe.authConfig={...te.authConfig},oe.storageConfig={...te.storageConfig};const e=ie[oe.catalog.type]||[];oe.properties={},e.forEach(e=>{oe.properties[e]=""}),oe.tableProperties={},oe.storageConfigArray.length=0,oe.authConfigArray.length=0}else{const t=await Object(i["c"])(e);if(!t)return;const{name:a,type:o,tableFormatList:c,storageConfig:l,authConfig:n,properties:r,tableProperties:s,optimizerGroup:u}=t;oe.catalog.name=a,oe.catalog.type=o,oe.catalog.optimizerGroup=u,oe.tableFormat=c.join(""),oe.authConfig=n,oe.storageConfig=l,oe.properties=r||{},oe.tableProperties=s||{},oe.storageConfigArray.length=0,oe.authConfigArray.length=0,ge()}oe.catalog.typeshow="ams"===oe.catalog.type?c["Internal Catalog"]:c["External Catalog"];const{storageConfig:a,authConfig:o}=oe;Object.keys(a).forEach(e=>{const t=["hadoop.core.site","hadoop.hdfs.site"];if(q.value&&t.push("hive.site"),t.includes(e)){var o,c,l;const t={key:e,label:ne[e],value:null===(o=a[e])||void 0===o?void 0:o.fileName,fileName:null===(c=a[e])||void 0===c?void 0:c.fileName,fileUrl:null===(l=a[e])||void 0===l?void 0:l.fileUrl,fileId:"",fileList:[],uploadLoading:!1,isSuccess:!1};oe.storageConfigArray.push(t)}}),Object.keys(o).forEach(e=>{if(["auth.kerberos.keytab","auth.kerberos.krb5"].includes(e)){var t,a,c;const l={key:e,label:re[e],value:null===(t=o[e])||void 0===t?void 0:t.fileName,fileName:null===(a=o[e])||void 0===a?void 0:a.fileName,fileUrl:null===(c=o[e])||void 0===c?void 0:c.fileUrl,fileId:"",fileList:[],uploadLoading:!1,isSuccess:!1};oe.authConfigArray.push(l)}})}catch(e){}finally{H.value=!1}}const Oe=e=>{e===c["Internal Catalog"]?oe.catalog.type="ams":oe.catalog.type=se[0].value,Ee()},je=Object(l["computed"])(()=>{const e=oe.catalog.type;return Y[e]||[]});async function me(){const e=await J.value.getPropertiesWithoputValidation(),t=ie[oe.catalog.type]||[];t.forEach(t=>{t&&!e[t]&&(e[t]="")});const a=ie[oe.tableFormat]||[];a.forEach(t=>{t&&!e[t]&&(e[t]="")});for(const o in e)e[o]||t.includes(o)||a.includes(o)||delete e[o];oe.properties=e}const he=Object(l["reactive"])([{label:"S3",value:"S3"}]),ve=Object(l["reactive"])([{label:"Hadoop",value:"Hadoop"}]),ke=Object(l["reactive"])([{label:"Hadoop",value:"Hadoop"},{label:"S3",value:"S3"}]),ye=Object(l["computed"])(()=>{const e=oe.catalog.type;return"ams"===e||"custom"===e?ke:"glue"===e?he:"hive"===e||"hadoop"===e?ve:null}),Ce=Object(l["computed"])(()=>{const e=oe.storageConfig["storage.type"];return"Hadoop"===e?ce:"S3"===e?le:null});async function Ee(){if(oe.tableFormat=je.value[0],!K.value)return;const e=oe.storageConfigArray.findIndex(e=>"hive.site"===e.key);if(q.value){if(e>-1)return;oe.storageConfigArray.push({key:"hive.site",label:ne["hive.site"],value:"",fileName:"",fileUrl:"",fileId:"",fileList:[],uploadLoading:!1,isSuccess:!1}),oe.storageConfig["hive.site"]=""}else e>-1&&(oe.storageConfigArray.splice(e,1),delete oe.storageConfig["hive.site"]);await me()}async function Be(){await me()}function Ne(){t("updateEdit",!0)}async function we(){const e=await Object(i["a"])(oe.catalog.name);e?Ve():o["a"].confirm({title:n("cannotDeleteModalTitle"),content:n("cannotDeleteModalContent"),wrapClassName:"not-delete-modal"})}async function Pe(e,t){return t?/^[a-zA-Z][\w-]*$/.test(t)?Promise.resolve():Promise.reject(new Error(n("invalidInput"))):Promise.reject(new Error(n("inputPlaceholder")))}function _e(){const{storageConfig:e,authConfig:t,storageConfigArray:a,authConfigArray:o}=oe;Object.keys(t).forEach(e=>{if(["auth.kerberos.keytab","auth.kerberos.krb5"].includes(e)){const a=(o.find(t=>t.key===e)||{}).fileId;t[e]=a}}),Object.keys(e).forEach(t=>{if(["hadoop.core.site","hadoop.hdfs.site","hive.site"].includes(t)){const o=(a.find(e=>e.key===t)||{}).fileId;e[t]=o}})}function $e(){X.value.validateFields().then(async()=>{const{catalog:e,tableFormat:a,storageConfig:o,authConfig:c}=oe,l=await J.value.getProperties(),s=await W.value.getProperties();if(!l)return;if(!s)return;H.value=!0;const{typeshow:u,...p}=e;_e(),await Object(i["g"])({isCreate:K.value,...p,tableFormatList:[a],storageConfig:o,authConfig:c,properties:l,tableProperties:s}).then(()=>{r["a"].success(`${n("save")} ${n("success")}`),t("updateEdit",!1,{catalogName:e.name,catalogType:e.type}),fe(),X.value.resetFields()}).catch(()=>{r["a"].error(`${n("save")} ${n("failed")}`)}).finally(()=>{H.value=!1})}).catch(()=>{})}function Se(){X.value.resetFields(),t("updateEdit",!1),fe()}async function Ve(){o["a"].confirm({title:n("deleteCatalogModalTitle"),onOk:async()=>{await Object(i["b"])(oe.catalog.name),r["a"].success(`${n("remove")} ${n("success")}`),t("updateEdit",!1,{})}})}function xe(e,t,a){try{if("uploading"===e.file.status?t.uploadLoading=!0:t.uploadLoading=!1,"done"===e.file.status){const{code:o}=e.file.response;if(200!==o)throw new Error("failed");const{url:c,id:l}=e.file.response.result;t.isSuccess=!0,t.fileName="STORAGE"===a?ee[t.key]:e.file.name,t.fileUrl=c,t.fileId=l,r["a"].success(`${e.file.name} ${n("uploaded")} ${n("success")}`)}else"error"===e.file.status&&(t.isSuccess=!1,r["a"].error(`${e.file.name} ${n("uploaded")} ${n("failed")}`))}catch(o){r["a"].error(`${n("uploaded")} ${n("failed")}`)}}function Ie(e){e&&window.open(e)}return Object(l["onMounted"])(()=>{de(),be()}),(e,t)=>{const a=Object(l["resolveComponent"])("a-form-item"),o=Object(l["resolveComponent"])("a-input"),n=Object(l["resolveComponent"])("a-select"),r=Object(l["resolveComponent"])("a-radio"),i=Object(l["resolveComponent"])("a-radio-group"),s=Object(l["resolveComponent"])("a-button"),p=Object(l["resolveComponent"])("a-upload"),b=Object(l["resolveComponent"])("a-form"),d=Object(l["resolveComponent"])("u-loading");return Object(l["openBlock"])(),Object(l["createElementBlock"])("div",g,[Object(l["createElementVNode"])("div",f,[Object(l["createElementVNode"])("div",O,[Object(l["createVNode"])(b,{ref_key:"formRef",ref:X,model:oe,class:"catalog-form"},{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createElementVNode"])("p",j,Object(l["toDisplayString"])(e.$t("basic")),1)]),_:1}),Object(l["createVNode"])(a,{label:e.$t("name"),name:["catalog","name"],rules:[{required:Object(l["unref"])(A)&&Object(l["unref"])(K),validator:Pe}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)&&Object(l["unref"])(K)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.catalog.name,"onUpdate:value":t[0]||(t[0]=e=>oe.catalog.name=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",m,Object(l["toDisplayString"])(oe.catalog.name),1))]),_:1},8,["label","rules"]),Object(l["createVNode"])(a,{label:e.$t("type"),name:["catalog","typeshow"]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)&&Object(l["unref"])(K)?(Object(l["openBlock"])(),Object(l["createBlock"])(n,{key:0,value:oe.catalog.typeshow,"onUpdate:value":t[1]||(t[1]=e=>oe.catalog.typeshow=e),options:ae.value,placeholder:T.selectPh,onChange:Oe},null,8,["value","options","placeholder"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",h,Object(l["toDisplayString"])(oe.catalog.typeshow),1))]),_:1},8,["label"]),oe.catalog.typeshow===c["External Catalog"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:0,label:e.$t("metastore"),name:["catalog","type"],rules:[{required:Object(l["unref"])(A)&&Object(l["unref"])(K)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)&&Object(l["unref"])(K)?(Object(l["openBlock"])(),Object(l["createBlock"])(n,{key:0,value:oe.catalog.type,"onUpdate:value":t[2]||(t[2]=e=>oe.catalog.type=e),options:se,placeholder:T.selectPh,onChange:Ee},null,8,["value","options","placeholder"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",v,Object(l["toDisplayString"])(L.value),1))]),_:1},8,["label","rules"])):Object(l["createCommentVNode"])("",!0),Object(l["createVNode"])(a,{label:e.$t("tableFormat"),name:["tableFormat"],rules:[{required:Object(l["unref"])(A)&&Object(l["unref"])(K)}]},{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(i,{disabled:!Object(l["unref"])(A)||!Object(l["unref"])(K),value:oe.tableFormat,"onUpdate:value":t[3]||(t[3]=e=>oe.tableFormat=e),name:"radioGroup",onChange:Be},{default:Object(l["withCtx"])(()=>[(Object(l["openBlock"])(!0),Object(l["createElementBlock"])(l["Fragment"],null,Object(l["renderList"])(Object(l["unref"])(je),e=>(Object(l["openBlock"])(),Object(l["createBlock"])(r,{key:e,value:e},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(Q[e]),1)]),_:2},1032,["value"]))),128))]),_:1},8,["disabled","value"])]),_:1},8,["label","rules"]),Object(l["createVNode"])(a,{label:e.$t("optimizerGroup"),name:["catalog","optimizerGroup"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(n,{key:0,value:oe.catalog.optimizerGroup,"onUpdate:value":t[4]||(t[4]=e=>oe.catalog.optimizerGroup=e),options:ue.value,placeholder:T.selectPh},null,8,["value","options","placeholder"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",k,Object(l["toDisplayString"])(oe.catalog.optimizerGroup),1))]),_:1},8,["label","rules"]),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createElementVNode"])("p",y,Object(l["toDisplayString"])(e.$t("storageConfigName")),1)]),_:1}),Object(l["createVNode"])(a,{label:"Type",name:["storageConfig","storage.type"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(n,{key:0,value:oe.storageConfig["storage.type"],"onUpdate:value":t[5]||(t[5]=e=>oe.storageConfig["storage.type"]=e),placeholder:T.selectPh,options:Object(l["unref"])(ye)},null,8,["value","placeholder","options"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",C,Object(l["toDisplayString"])(oe.storageConfig["storage.type"]),1))]),_:1},8,["name","rules"]),"S3"===oe.storageConfig["storage.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:1,label:"Endpoint",name:["storageConfig","storage.s3.endpoint"],rules:[{required:!1}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.storageConfig["storage.s3.endpoint"],"onUpdate:value":t[6]||(t[6]=e=>oe.storageConfig["storage.s3.endpoint"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",E,Object(l["toDisplayString"])(oe.storageConfig["storage.s3.endpoint"]),1))]),_:1},8,["name"])):Object(l["createCommentVNode"])("",!0),"S3"===oe.storageConfig["storage.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:2,label:"Region",name:["storageConfig","storage.s3.region"],rules:[{required:!1}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.storageConfig["storage.s3.region"],"onUpdate:value":t[7]||(t[7]=e=>oe.storageConfig["storage.s3.region"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",B,Object(l["toDisplayString"])(oe.storageConfig["storage.s3.region"]),1))]),_:1},8,["name"])):Object(l["createCommentVNode"])("",!0),"Hadoop"===oe.storageConfig["storage.type"]?(Object(l["openBlock"])(),Object(l["createElementBlock"])("div",N,[(Object(l["openBlock"])(!0),Object(l["createElementBlock"])(l["Fragment"],null,Object(l["renderList"])(oe.storageConfigArray,t=>(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:t.label,label:t.label,class:"g-flex-ac"},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(p,{key:0,"file-list":t.fileList,"onUpdate:file-list":e=>t.fileList=e,name:"file",accept:".xml",showUploadList:!1,action:Object(l["unref"])(F),disabled:t.uploadLoading,onChange:e=>xe(e,t,"STORAGE")},{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(s,{type:"primary",ghost:"",loading:t.uploadLoading,class:"g-mr-12"},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("upload")),1)]),_:2},1032,["loading"])]),_:2},1032,["file-list","onUpdate:file-list","action","disabled","onChange"])):Object(l["createCommentVNode"])("",!0),t.isSuccess||t.fileName?(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",{key:1,class:Object(l["normalizeClass"])(["config-value",{"view-active":!!t.fileUrl}]),onClick:e=>Ie(t.fileUrl)},Object(l["toDisplayString"])(t.fileName),11,w)):Object(l["createCommentVNode"])("",!0)]),_:2},1032,["label"]))),128))])):Object(l["createCommentVNode"])("",!0),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createElementVNode"])("p",P,Object(l["toDisplayString"])(e.$t("authenticationConfig")),1)]),_:1}),Object(l["createVNode"])(a,{label:"Type",name:["authConfig","auth.type"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(n,{key:0,value:oe.authConfig["auth.type"],"onUpdate:value":t[8]||(t[8]=e=>oe.authConfig["auth.type"]=e),placeholder:T.selectPh,options:Object(l["unref"])(Ce)},null,8,["value","placeholder","options"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",_,Object(l["toDisplayString"])(oe.authConfig["auth.type"]),1))]),_:1},8,["name","rules"]),"SIMPLE"===oe.authConfig["auth.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:4,label:"Hadoop Username",name:["authConfig","auth.simple.hadoop_username"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.authConfig["auth.simple.hadoop_username"],"onUpdate:value":t[9]||(t[9]=e=>oe.authConfig["auth.simple.hadoop_username"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",$,Object(l["toDisplayString"])(oe.authConfig["auth.simple.hadoop_username"]),1))]),_:1},8,["name","rules"])):Object(l["createCommentVNode"])("",!0),"KERBEROS"===oe.authConfig["auth.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:5,label:"Kerberos Principal",name:["authConfig","auth.kerberos.principal"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.authConfig["auth.kerberos.principal"],"onUpdate:value":t[10]||(t[10]=e=>oe.authConfig["auth.kerberos.principal"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",S,Object(l["toDisplayString"])(oe.authConfig["auth.kerberos.principal"]),1))]),_:1},8,["name","rules"])):Object(l["createCommentVNode"])("",!0),"KERBEROS"===oe.authConfig["auth.type"]?(Object(l["openBlock"])(),Object(l["createElementBlock"])("div",V,[(Object(l["openBlock"])(!0),Object(l["createElementBlock"])(l["Fragment"],null,Object(l["renderList"])(oe.authConfigArray,t=>(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:t.label,label:t.label,class:"g-flex-ac"},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(p,{key:0,"file-list":t.fileList,"onUpdate:file-list":e=>t.fileList=e,name:"file",accept:"auth.kerberos.keytab"===t.key?".keytab":".conf",showUploadList:!1,action:Object(l["unref"])(F),disabled:t.uploadLoading,onChange:e=>xe(e,t)},{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(s,{type:"primary",ghost:"",loading:t.uploadLoading,class:"g-mr-12"},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("upload")),1)]),_:2},1032,["loading"])]),_:2},1032,["file-list","onUpdate:file-list","accept","action","disabled","onChange"])):Object(l["createCommentVNode"])("",!0),t.isSuccess||t.fileName?(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",{key:1,class:Object(l["normalizeClass"])(["config-value auth-filename",{"view-active":!!t.fileUrl}]),onClick:e=>Ie(t.fileUrl),title:t.fileName},Object(l["toDisplayString"])(t.fileName),11,x)):Object(l["createCommentVNode"])("",!0)]),_:2},1032,["label"]))),128))])):Object(l["createCommentVNode"])("",!0),"AK/SK"===oe.authConfig["auth.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:7,label:"Access Key",name:["authConfig","auth.ak_sk.access_key"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.authConfig["auth.ak_sk.access_key"],"onUpdate:value":t[11]||(t[11]=e=>oe.authConfig["auth.ak_sk.access_key"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",I,Object(l["toDisplayString"])(oe.authConfig["auth.ak_sk.access_key"]),1))]),_:1},8,["name","rules"])):Object(l["createCommentVNode"])("",!0),"AK/SK"===oe.authConfig["auth.type"]?(Object(l["openBlock"])(),Object(l["createBlock"])(a,{key:8,label:"Secret Key",name:["authConfig","auth.ak_sk.secret_key"],rules:[{required:Object(l["unref"])(A)}]},{default:Object(l["withCtx"])(()=>[Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,value:oe.authConfig["auth.ak_sk.secret_key"],"onUpdate:value":t[12]||(t[12]=e=>oe.authConfig["auth.ak_sk.secret_key"]=e)},null,8,["value"])):(Object(l["openBlock"])(),Object(l["createElementBlock"])("span",z,Object(l["toDisplayString"])(oe.authConfig["auth.ak_sk.secret_key"]),1))]),_:1},8,["name","rules"])):Object(l["createCommentVNode"])("",!0),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createElementVNode"])("p",D,Object(l["toDisplayString"])(e.$t("properties")),1)]),_:1}),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(u["a"],{propertiesObj:oe.properties,isEdit:Object(l["unref"])(A),ref_key:"propertiesRef",ref:J},null,8,["propertiesObj","isEdit"])]),_:1}),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createElementVNode"])("p",G,Object(l["toDisplayString"])(e.$t("tableProperties")),1)]),_:1}),Object(l["createVNode"])(a,null,{default:Object(l["withCtx"])(()=>[Object(l["createVNode"])(u["a"],{propertiesObj:oe.tableProperties,isEdit:Object(l["unref"])(A),ref_key:"tablePropertiesRef",ref:W},null,8,["propertiesObj","isEdit"])]),_:1})]),_:1},8,["model"])])]),Object(l["unref"])(A)?(Object(l["openBlock"])(),Object(l["createElementBlock"])("div",U,[Object(l["createVNode"])(s,{type:"primary",onClick:$e,class:"save-btn g-mr-12"},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("save")),1)]),_:1}),Object(l["createVNode"])(s,{onClick:Se},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("cancel")),1)]),_:1})])):Object(l["createCommentVNode"])("",!0),Object(l["unref"])(A)?Object(l["createCommentVNode"])("",!0):(Object(l["openBlock"])(),Object(l["createElementBlock"])("div",R,[Object(l["createVNode"])(s,{type:"primary",onClick:Ne,class:"edit-btn g-mr-12"},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("edit")),1)]),_:1}),Object(l["createVNode"])(s,{onClick:we,class:"remove-btn"},{default:Object(l["withCtx"])(()=>[Object(l["createTextVNode"])(Object(l["toDisplayString"])(e.$t("remove")),1)]),_:1})])),H.value?(Object(l["openBlock"])(),Object(l["createBlock"])(d,{key:2})):Object(l["createCommentVNode"])("",!0)])}}}),T=(a("3c6b"),a("376e"),a("6b0d")),L=a.n(T);const A=L()(M,[["__scopeId","data-v-019ab485"]]);var F=A;const K={class:"catalogs-wrap g-flex"},q={class:"catalog-list-left"},H={class:"catalog-header"},X={key:0,class:"catalog-list"},J=["onClick"],W=Object(l["createTextVNode"])("+"),Z={class:"catalog-detail"};var Q=Object(l["defineComponent"])({__name:"index",setup(e){const{t:t}=Object(s["b"])(),a=Object(b["e"])(),r=Object(b["d"])(),i=Object(l["reactive"])([]),u=Object(l["reactive"])({}),p=Object(l["ref"])(!1),d="new catalog",g=Object(l["ref"])(!1),f=c["a"].PRESENTED_IMAGE_SIMPLE;async function O(){try{g.value=!0;const e=await Object(n["a"])();i.length=0,(e||[]).forEach(e=>{i.push({catalogName:e.catalogName,catalogType:e.catalogType})})}finally{g.value=!1}}function j(){const{catalogname:e="",type:t}=r.query,a={};if(decodeURIComponent(e)!==d){var o,c;if(e)a.catalogName=e,a.catalogType=t;else a.catalogName=null===(o=i[0])||void 0===o?void 0:o.catalogName,a.catalogType=null===(c=i[0])||void 0===c?void 0:c.catalogType;h(a)}else y()}function m(e){p.value?E(()=>{h(e),p.value=!1,v(!1)}):h(e)}async function h(e){const{catalogName:t,catalogType:o}=e;u.catalogName=t||"",u.catalogType=o||"",await a.replace({path:"/catalogs",query:{catalogname:encodeURIComponent(u.catalogName),type:u.catalogType}})}async function v(e,t){var a,o;(p.value=e,t)&&(await k(),null!==t&&void 0!==t&&t.catalogName||(t.catalogName=null===(a=i[0])||void 0===a?void 0:a.catalogName,t.catalogType=null===(o=i[0])||void 0===o?void 0:o.catalogType));const c=i.findIndex(e=>e.catalogName===d);if(c>-1){var l,n;i.splice(c);const e={catalogName:null===(l=i[0])||void 0===l?void 0:l.catalogName,catalogType:null===(n=i[0])||void 0===n?void 0:n.catalogType};h(e)}else t&&h(t)}async function k(){await O()}function y(){p.value?E(()=>{C()}):C()}async function C(){const e={catalogName:d,catalogType:""};await h(e),i.push(e),p.value=!0}function E(e){o["a"].confirm({title:t("leavePageModalTitle"),content:t("leavePageModalContent"),okText:t("leave"),onOk:async()=>{e&&await e()}})}return Object(l["onMounted"])(async()=>{await O(),j()}),Object(b["c"])((e,t,a)=>{p.value?E(()=>{a()}):a()}),(e,t)=>{const a=Object(l["resolveComponent"])("a-button"),o=Object(l["resolveComponent"])("a-empty");return Object(l["openBlock"])(),Object(l["createElementBlock"])("div",K,[Object(l["createElementVNode"])("div",q,[Object(l["createElementVNode"])("div",H,Object(l["toDisplayString"])(`${e.$t("catalog")} ${e.$t("list")}`),1),i.length&&!g.value?(Object(l["openBlock"])(),Object(l["createElementBlock"])("ul",X,[(Object(l["openBlock"])(!0),Object(l["createElementBlock"])(l["Fragment"],null,Object(l["renderList"])(i,e=>(Object(l["openBlock"])(),Object(l["createElementBlock"])("li",{key:e.catalogName,class:Object(l["normalizeClass"])(["catalog-item g-text-nowrap",{active:e.catalogName===u.catalogName}]),onClick:t=>m(e)},Object(l["toDisplayString"])(e.catalogName),11,J))),128))])):Object(l["createCommentVNode"])("",!0),Object(l["createVNode"])(a,{onClick:y,disabled:u.catalogName===d,class:"add-btn"},{default:Object(l["withCtx"])(()=>[W]),_:1},8,["disabled"])]),Object(l["createElementVNode"])("div",Z,[i.length||g.value?(Object(l["openBlock"])(),Object(l["createBlock"])(F,{key:1,isEdit:p.value,onUpdateEdit:v,onUpdateCatalogs:k},null,8,["isEdit"])):(Object(l["openBlock"])(),Object(l["createBlock"])(o,{key:0,image:Object(l["unref"])(f),class:"detail-empty"},null,8,["image"]))])])}}});a("c5c7");const Y=L()(Q,[["__scopeId","data-v-0914312d"]]);t["default"]=Y},c5c7:function(e,t,a){"use strict";a("9df0")},e723:function(e,t,a){"use strict";a.d(t,"d",(function(){return c})),a.d(t,"c",(function(){return l})),a.d(t,"i",(function(){return n})),a.d(t,"h",(function(){return r})),a.d(t,"e",(function(){return i})),a.d(t,"b",(function(){return s})),a.d(t,"a",(function(){return u})),a.d(t,"j",(function(){return p})),a.d(t,"g",(function(){return b})),a.d(t,"f",(function(){return d}));var o=a("b32d");function c(e){const{optimizerGroup:t,page:a,pageSize:c}=e;return o["a"].get(`ams/v1/optimize/optimizerGroups/${t}/tables`,{params:{page:a,pageSize:c}})}function l(e){const{optimizerGroup:t,page:a,pageSize:c}=e;return o["a"].get(`ams/v1/optimize/optimizerGroups/${t}/optimizers`,{params:{page:a,pageSize:c}})}function n(e){const{optimizerGroup:t,parallelism:a}=e;return o["a"].post(`ams/v1/optimize/optimizerGroups/${t}/optimizers`,{parallelism:a})}function r(e){const{optimizerGroup:t,jobId:a}=e;return o["a"].delete(`ams/v1/optimize/optimizerGroups/${t}/optimizers/${a}`)}async function i(){const e=await o["a"].get("ams/v1/optimize/resourceGroups");return e}const s=async()=>{const e=await o["a"].get("ams/v1/optimize/containers/get");return e},u=e=>o["a"].post("ams/v1/optimize/resourceGroups",e),p=e=>o["a"].put("ams/v1/optimize/resourceGroups",e),b=e=>o["a"].get(`/ams/v1/optimize/resourceGroups/${e.name}/delete/check`),d=e=>o["a"].delete("/ams/v1/optimize/resourceGroups/"+e.name)}}]);