import{M as d,N as l}from"./index--2c5DB0I.js";function v(){const{t:e}=d(),t=l(()=>e("catalog")).value,a=l(()=>e("databaseName")).value,h=l(()=>e("tableName")).value,P=l(()=>e("optimzerGroup")).value,u=l(()=>e("resourceGroup")).value,o=l(()=>e("parallelism")).value,r=l(()=>e("username")).value,n=l(()=>e("password")).value,c=l(()=>e("database",2)).value,s=l(()=>e("table",2)).value,p=l(()=>e("name")).value,i=l(()=>e("container")).value;return{selectPh:e("selectPlaceholder"),inputPh:e("inputPlaceholder"),selectClPh:e("selectPlaceholder",{selectPh:t}),selectDBPh:e("selectPlaceholder",{selectPh:a}),inputDBPh:e("inputPlaceholder",{inputPh:a}),inputClPh:e("inputPlaceholder",{inputPh:t}),inputTNPh:e("inputPlaceholder",{inputPh:h}),selectOptGroupPh:e("inputPlaceholder",{inputPh:P}),resourceGroupPh:e("inputPlaceholder",{inputPh:u}),parallelismPh:e("inputPlaceholder",{inputPh:o}),usernamePh:e("inputPlaceholder",{inputPh:r}),passwordPh:e("inputPlaceholder",{inputPh:n}),filterDBPh:e("filterPlaceholder",{inputPh:c}),filterTablePh:e("filterPlaceholder",{inputPh:s}),groupNamePh:e("inputPlaceholder",{inputPh:p}),groupContainer:e("selectPlaceholder",{selectPh:i})}}export{v as u};
