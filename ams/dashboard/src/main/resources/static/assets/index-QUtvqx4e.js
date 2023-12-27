
/*
  * Licensed to the Apache Software Foundation (ASF) under one
  * or more contributor license agreements.  See the NOTICE file
  * distributed with this work for additional information
  * regarding copyright ownership.  The ASF licenses this file
  * to you under the Apache License, Version 2.0 (the
  * "License"); you may not use this file except in compliance
  * with the License.  You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

import{v as D,U as N,K as O,L as P,D as K,C as v,a7 as T,r as U,o,f as b,z as s,c as d,i as l,b as c,h as S,F as C,l as E,af as V,ao as F,aD as L,ap as R,y as u,aE as G,x as H}from"./index-Cv3Z2rcY.js";/* empty css              *//* empty css              *//* empty css              *//* empty css              *//* empty css              *//* empty css              */import{b as J,e as Q}from"./setting.services-SEX7a7CS.js";const W={class:"setting-wrap"},X={class:"system-setting"},Y={class:"container-setting"},Z={class:"content"},ee={class:"item"},te={class:"left"},ae={class:"right"},se={key:0,class:"item"},ne={class:"left"},oe={class:"right"},re={class:"g-mb-12 g-mt-12"},ie={class:"g-mb-12 g-mt-12"},le=D({__name:"index",setup(ce){const{t:r}=N(),j=O(),I=P(),p=K(!1),h=v([]),f=v([]);v([{title:r("name"),dataIndex:"name",width:340,ellipsis:!0},{title:r("propertiesMemory",{type:"taskmanager"}),dataIndex:"tmMemory",width:"50%",ellipsis:!0},{title:r("propertiesMemory",{type:"jobmanager"}),dataIndex:"jmMemory",width:"50%",ellipsis:!0}]);const k=v([{title:r("key"),dataIndex:"key",width:340,ellipsis:!0},{title:r("value"),dataIndex:"value"}]),g=K([]),i={system:{title:r("systemSetting"),key:"system"},container:{title:r("containerSetting"),key:"container"}},m=K(i.system.key);T(()=>I,t=>{var e;m.value=((e=t.query)==null?void 0:e.tab)||i.system.key,x(m.value)},{immediate:!0});async function M(){try{p.value=!0;const t=await J();if(!t)return;h.length=0,Object.keys(t).forEach(e=>{h.push({key:e,value:t[e]})})}finally{p.value=!1}}async function $(){try{p.value=!0;const t=await Q();g.value=[],f.length=0,(t||[]).forEach((e,_)=>{e.propertiesArray=[],g.value.push(e.name),f.push(e),Object.keys(e.properties||{}).forEach(n=>{f[_].propertiesArray.push({key:n,value:e.properties[n]})}),(e.optimizeGroup||[]).forEach(n=>{n.innerPropertiesArray=[],Object.keys(n.properties||{}).forEach(y=>{n.innerPropertiesArray.push({key:y,value:n.properties[y]})})})})}finally{p.value=!1}}function x(t){const e={...I.query};e.tab=t,j.replace({query:{...e}}),q()}function q(){m.value===i.system.key?M():$()}return(t,e)=>{const _=V,n=F,y=G,A=L,z=R,B=U("u-loading");return o(),b(C,null,[s("div",W,[d(z,{activeKey:m.value,"onUpdate:activeKey":e[1]||(e[1]=a=>m.value=a),onChange:x},{default:l(()=>[(o(),c(n,{key:i.system.key,tab:i.system.title},{default:l(()=>[s("div",X,[h.length?(o(),c(_,{key:0,rowKey:"key",columns:k,"data-source":h,pagination:!1},null,8,["columns","data-source"])):S("",!0)])]),_:1},8,["tab"])),(o(),c(n,{key:i.container.key,tab:i.container.title},{default:l(()=>[s("div",Y,[d(A,{activeKey:g.value,"onUpdate:activeKey":e[0]||(e[0]=a=>g.value=a)},{default:l(()=>[(o(!0),b(C,null,E(f,a=>(o(),c(y,{key:a.name,header:a.name},{default:l(()=>[s("ul",Z,[s("li",ee,[s("h3",te,u(t.$t("name")),1),s("span",ae,u(a.name),1)]),a.classpath?(o(),b("li",se,[s("h3",ne,u(t.$t("implementation")),1),s("span",oe,u(a.classpath),1)])):S("",!0)]),s("h3",re,u(t.$t("properties")),1),d(_,{rowKey:"key",columns:k,"data-source":a.propertiesArray,pagination:!1},null,8,["columns","data-source"]),s("h3",ie,u(t.$t("optimizerGroups")),1),d(A,null,{default:l(()=>[(o(!0),b(C,null,E(a.optimizeGroup,w=>(o(),c(y,{key:w.name,header:w.name},{default:l(()=>[d(_,{rowKey:"name",columns:k,"data-source":w.innerPropertiesArray,pagination:!1},null,8,["columns","data-source"])]),_:2},1032,["header"]))),128))]),_:2},1024)]),_:2},1032,["header"]))),128))]),_:1},8,["activeKey"])])]),_:1},8,["tab"]))]),_:1},8,["activeKey"])]),p.value?(o(),c(B,{key:0})):S("",!0)],64)}}}),ge=H(le,[["__scopeId","data-v-fc61ecef"]]);export{ge as default};
