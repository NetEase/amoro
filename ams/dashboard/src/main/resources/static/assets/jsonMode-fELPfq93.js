var Se=12e4,Me=function(){function e(t){var n=this;this._defaults=t,this._worker=null,this._idleCheckInterval=setInterval(function(){return n._checkIfIdle()},30*1e3),this._lastUsedTime=0,this._configChangeListener=this._defaults.onDidChange(function(){return n._stopWorker()})}return e.prototype._stopWorker=function(){this._worker&&(this._worker.dispose(),this._worker=null),this._client=null},e.prototype.dispose=function(){clearInterval(this._idleCheckInterval),this._configChangeListener.dispose(),this._stopWorker()},e.prototype._checkIfIdle=function(){if(this._worker){var t=Date.now()-this._lastUsedTime;t>Se&&this._stopWorker()}},e.prototype._getClient=function(){return this._lastUsedTime=Date.now(),this._client||(this._worker=monaco.editor.createWebWorker({moduleId:"vs/language/json/jsonWorker",label:this._defaults.languageId,createData:{languageSettings:this._defaults.diagnosticsOptions,languageId:this._defaults.languageId,enableSchemaRequest:this._defaults.diagnosticsOptions.enableSchemaRequest}}),this._client=this._worker.getProxy()),this._client},e.prototype.getLanguageServiceWorker=function(){for(var t=this,n=[],i=0;i<arguments.length;i++)n[i]=arguments[i];var r;return this._getClient().then(function(a){r=a}).then(function(a){return t._worker.withSyncedResources(n)}).then(function(a){return r})},e}(),C;(function(e){function t(i,r){return{line:i,character:r}}e.create=t;function n(i){var r=i;return s.objectLiteral(r)&&s.number(r.line)&&s.number(r.character)}e.is=n})(C||(C={}));var b;(function(e){function t(i,r,a,o){if(s.number(i)&&s.number(r)&&s.number(a)&&s.number(o))return{start:C.create(i,r),end:C.create(a,o)};if(C.is(i)&&C.is(r))return{start:i,end:r};throw new Error("Range#create called with invalid arguments["+i+", "+r+", "+a+", "+o+"]")}e.create=t;function n(i){var r=i;return s.objectLiteral(r)&&C.is(r.start)&&C.is(r.end)}e.is=n})(b||(b={}));var z;(function(e){function t(i,r){return{uri:i,range:r}}e.create=t;function n(i){var r=i;return s.defined(r)&&b.is(r.range)&&(s.string(r.uri)||s.undefined(r.uri))}e.is=n})(z||(z={}));var G;(function(e){function t(i,r,a,o){return{targetUri:i,targetRange:r,targetSelectionRange:a,originSelectionRange:o}}e.create=t;function n(i){var r=i;return s.defined(r)&&b.is(r.targetRange)&&s.string(r.targetUri)&&(b.is(r.targetSelectionRange)||s.undefined(r.targetSelectionRange))&&(b.is(r.originSelectionRange)||s.undefined(r.originSelectionRange))}e.is=n})(G||(G={}));var B;(function(e){function t(i,r,a,o){return{red:i,green:r,blue:a,alpha:o}}e.create=t;function n(i){var r=i;return s.number(r.red)&&s.number(r.green)&&s.number(r.blue)&&s.number(r.alpha)}e.is=n})(B||(B={}));var Q;(function(e){function t(i,r){return{range:i,color:r}}e.create=t;function n(i){var r=i;return b.is(r.range)&&B.is(r.color)}e.is=n})(Q||(Q={}));var X;(function(e){function t(i,r,a){return{label:i,textEdit:r,additionalTextEdits:a}}e.create=t;function n(i){var r=i;return s.string(r.label)&&(s.undefined(r.textEdit)||E.is(r))&&(s.undefined(r.additionalTextEdits)||s.typedArray(r.additionalTextEdits,E.is))}e.is=n})(X||(X={}));var M;(function(e){e.Comment="comment",e.Imports="imports",e.Region="region"})(M||(M={}));var Z;(function(e){function t(i,r,a,o,u){var d={startLine:i,endLine:r};return s.defined(a)&&(d.startCharacter=a),s.defined(o)&&(d.endCharacter=o),s.defined(u)&&(d.kind=u),d}e.create=t;function n(i){var r=i;return s.number(r.startLine)&&s.number(r.startLine)&&(s.undefined(r.startCharacter)||s.number(r.startCharacter))&&(s.undefined(r.endCharacter)||s.number(r.endCharacter))&&(s.undefined(r.kind)||s.string(r.kind))}e.is=n})(Z||(Z={}));var $;(function(e){function t(i,r){return{location:i,message:r}}e.create=t;function n(i){var r=i;return s.defined(r)&&z.is(r.location)&&s.string(r.message)}e.is=n})($||($={}));var y;(function(e){e.Error=1,e.Warning=2,e.Information=3,e.Hint=4})(y||(y={}));var N;(function(e){function t(i,r,a,o,u,d){var l={range:i,message:r};return s.defined(a)&&(l.severity=a),s.defined(o)&&(l.code=o),s.defined(u)&&(l.source=u),s.defined(d)&&(l.relatedInformation=d),l}e.create=t;function n(i){var r=i;return s.defined(r)&&b.is(r.range)&&s.string(r.message)&&(s.number(r.severity)||s.undefined(r.severity))&&(s.number(r.code)||s.string(r.code)||s.undefined(r.code))&&(s.string(r.source)||s.undefined(r.source))&&(s.undefined(r.relatedInformation)||s.typedArray(r.relatedInformation,$.is))}e.is=n})(N||(N={}));var O;(function(e){function t(i,r){for(var a=[],o=2;o<arguments.length;o++)a[o-2]=arguments[o];var u={title:i,command:r};return s.defined(a)&&a.length>0&&(u.arguments=a),u}e.create=t;function n(i){var r=i;return s.defined(r)&&s.string(r.title)&&s.string(r.command)}e.is=n})(O||(O={}));var E;(function(e){function t(a,o){return{range:a,newText:o}}e.replace=t;function n(a,o){return{range:{start:a,end:a},newText:o}}e.insert=n;function i(a){return{range:a,newText:""}}e.del=i;function r(a){var o=a;return s.objectLiteral(o)&&s.string(o.newText)&&b.is(o.range)}e.is=r})(E||(E={}));var x;(function(e){function t(i,r){return{textDocument:i,edits:r}}e.create=t;function n(i){var r=i;return s.defined(r)&&U.is(r.textDocument)&&Array.isArray(r.edits)}e.is=n})(x||(x={}));var D;(function(e){function t(i,r){var a={kind:"create",uri:i};return r!==void 0&&(r.overwrite!==void 0||r.ignoreIfExists!==void 0)&&(a.options=r),a}e.create=t;function n(i){var r=i;return r&&r.kind==="create"&&s.string(r.uri)&&(r.options===void 0||(r.options.overwrite===void 0||s.boolean(r.options.overwrite))&&(r.options.ignoreIfExists===void 0||s.boolean(r.options.ignoreIfExists)))}e.is=n})(D||(D={}));var j;(function(e){function t(i,r,a){var o={kind:"rename",oldUri:i,newUri:r};return a!==void 0&&(a.overwrite!==void 0||a.ignoreIfExists!==void 0)&&(o.options=a),o}e.create=t;function n(i){var r=i;return r&&r.kind==="rename"&&s.string(r.oldUri)&&s.string(r.newUri)&&(r.options===void 0||(r.options.overwrite===void 0||s.boolean(r.options.overwrite))&&(r.options.ignoreIfExists===void 0||s.boolean(r.options.ignoreIfExists)))}e.is=n})(j||(j={}));var W;(function(e){function t(i,r){var a={kind:"delete",uri:i};return r!==void 0&&(r.recursive!==void 0||r.ignoreIfNotExists!==void 0)&&(a.options=r),a}e.create=t;function n(i){var r=i;return r&&r.kind==="delete"&&s.string(r.uri)&&(r.options===void 0||(r.options.recursive===void 0||s.boolean(r.options.recursive))&&(r.options.ignoreIfNotExists===void 0||s.boolean(r.options.ignoreIfNotExists)))}e.is=n})(W||(W={}));var q;(function(e){function t(n){var i=n;return i&&(i.changes!==void 0||i.documentChanges!==void 0)&&(i.documentChanges===void 0||i.documentChanges.every(function(r){return s.string(r.kind)?D.is(r)||j.is(r)||W.is(r):x.is(r)}))}e.is=t})(q||(q={}));var F=function(){function e(t){this.edits=t}return e.prototype.insert=function(t,n){this.edits.push(E.insert(t,n))},e.prototype.replace=function(t,n){this.edits.push(E.replace(t,n))},e.prototype.delete=function(t){this.edits.push(E.del(t))},e.prototype.add=function(t){this.edits.push(t)},e.prototype.all=function(){return this.edits},e.prototype.clear=function(){this.edits.splice(0,this.edits.length)},e}();(function(){function e(t){var n=this;this._textEditChanges=Object.create(null),t&&(this._workspaceEdit=t,t.documentChanges?t.documentChanges.forEach(function(i){if(x.is(i)){var r=new F(i.edits);n._textEditChanges[i.textDocument.uri]=r}}):t.changes&&Object.keys(t.changes).forEach(function(i){var r=new F(t.changes[i]);n._textEditChanges[i]=r}))}return Object.defineProperty(e.prototype,"edit",{get:function(){return this._workspaceEdit},enumerable:!0,configurable:!0}),e.prototype.getTextEditChange=function(t){if(U.is(t)){if(this._workspaceEdit||(this._workspaceEdit={documentChanges:[]}),!this._workspaceEdit.documentChanges)throw new Error("Workspace edit is not configured for document changes.");var n=t,i=this._textEditChanges[n.uri];if(!i){var r=[],a={textDocument:n,edits:r};this._workspaceEdit.documentChanges.push(a),i=new F(r),this._textEditChanges[n.uri]=i}return i}else{if(this._workspaceEdit||(this._workspaceEdit={changes:Object.create(null)}),!this._workspaceEdit.changes)throw new Error("Workspace edit is not configured for normal text edit changes.");var i=this._textEditChanges[t];if(!i){var r=[];this._workspaceEdit.changes[t]=r,i=new F(r),this._textEditChanges[t]=i}return i}},e.prototype.createFile=function(t,n){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(D.create(t,n))},e.prototype.renameFile=function(t,n,i){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(j.create(t,n,i))},e.prototype.deleteFile=function(t,n){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(W.create(t,n))},e.prototype.checkDocumentChanges=function(){if(!this._workspaceEdit||!this._workspaceEdit.documentChanges)throw new Error("Workspace edit is not configured for document changes.")},e})();var K;(function(e){function t(i){return{uri:i}}e.create=t;function n(i){var r=i;return s.defined(r)&&s.string(r.uri)}e.is=n})(K||(K={}));var U;(function(e){function t(i,r){return{uri:i,version:r}}e.create=t;function n(i){var r=i;return s.defined(r)&&s.string(r.uri)&&(r.version===null||s.number(r.version))}e.is=n})(U||(U={}));var ee;(function(e){function t(i,r,a,o){return{uri:i,languageId:r,version:a,text:o}}e.create=t;function n(i){var r=i;return s.defined(r)&&s.string(r.uri)&&s.string(r.languageId)&&s.number(r.version)&&s.string(r.text)}e.is=n})(ee||(ee={}));var I;(function(e){e.PlainText="plaintext",e.Markdown="markdown"})(I||(I={}));(function(e){function t(n){var i=n;return i===e.PlainText||i===e.Markdown}e.is=t})(I||(I={}));var J;(function(e){function t(n){var i=n;return s.objectLiteral(n)&&I.is(i.kind)&&s.string(i.value)}e.is=t})(J||(J={}));var v;(function(e){e.Text=1,e.Method=2,e.Function=3,e.Constructor=4,e.Field=5,e.Variable=6,e.Class=7,e.Interface=8,e.Module=9,e.Property=10,e.Unit=11,e.Value=12,e.Enum=13,e.Keyword=14,e.Snippet=15,e.Color=16,e.File=17,e.Reference=18,e.Folder=19,e.EnumMember=20,e.Constant=21,e.Struct=22,e.Event=23,e.Operator=24,e.TypeParameter=25})(v||(v={}));var Y;(function(e){e.PlainText=1,e.Snippet=2})(Y||(Y={}));var re;(function(e){function t(n){return{label:n}}e.create=t})(re||(re={}));var te;(function(e){function t(n,i){return{items:n||[],isIncomplete:!!i}}e.create=t})(te||(te={}));var V;(function(e){function t(i){return i.replace(/[\\`*_{}[\]()#+\-.!]/g,"\\$&")}e.fromPlainText=t;function n(i){var r=i;return s.string(r)||s.objectLiteral(r)&&s.string(r.language)&&s.string(r.value)}e.is=n})(V||(V={}));var ne;(function(e){function t(n){var i=n;return!!i&&s.objectLiteral(i)&&(J.is(i.contents)||V.is(i.contents)||s.typedArray(i.contents,V.is))&&(n.range===void 0||b.is(n.range))}e.is=t})(ne||(ne={}));var ie;(function(e){function t(n,i){return i?{label:n,documentation:i}:{label:n}}e.create=t})(ie||(ie={}));var ae;(function(e){function t(n,i){for(var r=[],a=2;a<arguments.length;a++)r[a-2]=arguments[a];var o={label:n};return s.defined(i)&&(o.documentation=i),s.defined(r)?o.parameters=r:o.parameters=[],o}e.create=t})(ae||(ae={}));var oe;(function(e){e.Text=1,e.Read=2,e.Write=3})(oe||(oe={}));var se;(function(e){function t(n,i){var r={range:n};return s.number(i)&&(r.kind=i),r}e.create=t})(se||(se={}));var p;(function(e){e.File=1,e.Module=2,e.Namespace=3,e.Package=4,e.Class=5,e.Method=6,e.Property=7,e.Field=8,e.Constructor=9,e.Enum=10,e.Interface=11,e.Function=12,e.Variable=13,e.Constant=14,e.String=15,e.Number=16,e.Boolean=17,e.Array=18,e.Object=19,e.Key=20,e.Null=21,e.EnumMember=22,e.Struct=23,e.Event=24,e.Operator=25,e.TypeParameter=26})(p||(p={}));var ue;(function(e){function t(n,i,r,a,o){var u={name:n,kind:i,location:{uri:a,range:r}};return o&&(u.containerName=o),u}e.create=t})(ue||(ue={}));var ce=function(){function e(){}return e}();(function(e){function t(i,r,a,o,u,d){var l={name:i,detail:r,kind:a,range:o,selectionRange:u};return d!==void 0&&(l.children=d),l}e.create=t;function n(i){var r=i;return r&&s.string(r.name)&&s.number(r.kind)&&b.is(r.range)&&b.is(r.selectionRange)&&(r.detail===void 0||s.string(r.detail))&&(r.deprecated===void 0||s.boolean(r.deprecated))&&(r.children===void 0||Array.isArray(r.children))}e.is=n})(ce||(ce={}));var fe;(function(e){e.QuickFix="quickfix",e.Refactor="refactor",e.RefactorExtract="refactor.extract",e.RefactorInline="refactor.inline",e.RefactorRewrite="refactor.rewrite",e.Source="source",e.SourceOrganizeImports="source.organizeImports"})(fe||(fe={}));var de;(function(e){function t(i,r){var a={diagnostics:i};return r!=null&&(a.only=r),a}e.create=t;function n(i){var r=i;return s.defined(r)&&s.typedArray(r.diagnostics,N.is)&&(r.only===void 0||s.typedArray(r.only,s.string))}e.is=n})(de||(de={}));var le;(function(e){function t(i,r,a){var o={title:i};return O.is(r)?o.command=r:o.edit=r,a!==void 0&&(o.kind=a),o}e.create=t;function n(i){var r=i;return r&&s.string(r.title)&&(r.diagnostics===void 0||s.typedArray(r.diagnostics,N.is))&&(r.kind===void 0||s.string(r.kind))&&(r.edit!==void 0||r.command!==void 0)&&(r.command===void 0||O.is(r.command))&&(r.edit===void 0||q.is(r.edit))}e.is=n})(le||(le={}));var ge;(function(e){function t(i,r){var a={range:i};return s.defined(r)&&(a.data=r),a}e.create=t;function n(i){var r=i;return s.defined(r)&&b.is(r.range)&&(s.undefined(r.command)||O.is(r.command))}e.is=n})(ge||(ge={}));var he;(function(e){function t(i,r){return{tabSize:i,insertSpaces:r}}e.create=t;function n(i){var r=i;return s.defined(r)&&s.number(r.tabSize)&&s.boolean(r.insertSpaces)}e.is=n})(he||(he={}));var ve=function(){function e(){}return e}();(function(e){function t(i,r,a){return{range:i,target:r,data:a}}e.create=t;function n(i){var r=i;return s.defined(r)&&b.is(r.range)&&(s.undefined(r.target)||s.string(r.target))}e.is=n})(ve||(ve={}));var pe;(function(e){function t(a,o,u,d){return new Oe(a,o,u,d)}e.create=t;function n(a){var o=a;return!!(s.defined(o)&&s.string(o.uri)&&(s.undefined(o.languageId)||s.string(o.languageId))&&s.number(o.lineCount)&&s.func(o.getText)&&s.func(o.positionAt)&&s.func(o.offsetAt))}e.is=n;function i(a,o){for(var u=a.getText(),d=r(o,function(R,f){var m=R.range.start.line-f.range.start.line;return m===0?R.range.start.character-f.range.start.character:m}),l=u.length,c=d.length-1;c>=0;c--){var h=d[c],g=a.offsetAt(h.range.start),A=a.offsetAt(h.range.end);if(A<=l)u=u.substring(0,g)+h.newText+u.substring(A,u.length);else throw new Error("Overlapping edit");l=g}return u}e.applyEdits=i;function r(a,o){if(a.length<=1)return a;var u=a.length/2|0,d=a.slice(0,u),l=a.slice(u);r(d,o),r(l,o);for(var c=0,h=0,g=0;c<d.length&&h<l.length;){var A=o(d[c],l[h]);A<=0?a[g++]=d[c++]:a[g++]=l[h++]}for(;c<d.length;)a[g++]=d[c++];for(;h<l.length;)a[g++]=l[h++];return a}})(pe||(pe={}));var me;(function(e){e.Manual=1,e.AfterDelay=2,e.FocusOut=3})(me||(me={}));var Oe=function(){function e(t,n,i,r){this._uri=t,this._languageId=n,this._version=i,this._content=r,this._lineOffsets=null}return Object.defineProperty(e.prototype,"uri",{get:function(){return this._uri},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"languageId",{get:function(){return this._languageId},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"version",{get:function(){return this._version},enumerable:!0,configurable:!0}),e.prototype.getText=function(t){if(t){var n=this.offsetAt(t.start),i=this.offsetAt(t.end);return this._content.substring(n,i)}return this._content},e.prototype.update=function(t,n){this._content=t.text,this._version=n,this._lineOffsets=null},e.prototype.getLineOffsets=function(){if(this._lineOffsets===null){for(var t=[],n=this._content,i=!0,r=0;r<n.length;r++){i&&(t.push(r),i=!1);var a=n.charAt(r);i=a==="\r"||a===`
`,a==="\r"&&r+1<n.length&&n.charAt(r+1)===`
`&&r++}i&&n.length>0&&t.push(n.length),this._lineOffsets=t}return this._lineOffsets},e.prototype.positionAt=function(t){t=Math.max(Math.min(t,this._content.length),0);var n=this.getLineOffsets(),i=0,r=n.length;if(r===0)return C.create(0,t);for(;i<r;){var a=Math.floor((i+r)/2);n[a]>t?r=a:i=a+1}var o=i-1;return C.create(o,t-n[o])},e.prototype.offsetAt=function(t){var n=this.getLineOffsets();if(t.line>=n.length)return this._content.length;if(t.line<0)return 0;var i=n[t.line],r=t.line+1<n.length?n[t.line+1]:this._content.length;return Math.max(Math.min(i+t.character,r),i)},Object.defineProperty(e.prototype,"lineCount",{get:function(){return this.getLineOffsets().length},enumerable:!0,configurable:!0}),e}(),s;(function(e){var t=Object.prototype.toString;function n(c){return typeof c<"u"}e.defined=n;function i(c){return typeof c>"u"}e.undefined=i;function r(c){return c===!0||c===!1}e.boolean=r;function a(c){return t.call(c)==="[object String]"}e.string=a;function o(c){return t.call(c)==="[object Number]"}e.number=o;function u(c){return t.call(c)==="[object Function]"}e.func=u;function d(c){return c!==null&&typeof c=="object"}e.objectLiteral=d;function l(c,h){return Array.isArray(c)&&c.every(h)}e.typedArray=l})(s||(s={}));monaco.Uri;var Ce=monaco.Range,Ie=function(){function e(t,n,i){var r=this;this._languageId=t,this._worker=n,this._disposables=[],this._listener=Object.create(null);var a=function(u){var d=u.getModeId();if(d===r._languageId){var l;r._listener[u.uri.toString()]=u.onDidChangeContent(function(){clearTimeout(l),l=setTimeout(function(){return r._doValidate(u.uri,d)},500)}),r._doValidate(u.uri,d)}},o=function(u){monaco.editor.setModelMarkers(u,r._languageId,[]);var d=u.uri.toString(),l=r._listener[d];l&&(l.dispose(),delete r._listener[d])};this._disposables.push(monaco.editor.onDidCreateModel(a)),this._disposables.push(monaco.editor.onWillDisposeModel(function(u){o(u),r._resetSchema(u.uri)})),this._disposables.push(monaco.editor.onDidChangeModelLanguage(function(u){o(u.model),a(u.model),r._resetSchema(u.model.uri)})),this._disposables.push(i.onDidChange(function(u){monaco.editor.getModels().forEach(function(d){d.getModeId()===r._languageId&&(o(d),a(d))})})),this._disposables.push({dispose:function(){monaco.editor.getModels().forEach(o);for(var u in r._listener)r._listener[u].dispose()}}),monaco.editor.getModels().forEach(a)}return e.prototype.dispose=function(){this._disposables.forEach(function(t){return t&&t.dispose()}),this._disposables=[]},e.prototype._resetSchema=function(t){this._worker().then(function(n){n.resetSchema(t.toString())})},e.prototype._doValidate=function(t,n){this._worker(t).then(function(i){return i.doValidation(t.toString()).then(function(r){var a=r.map(function(u){return Re(t,u)}),o=monaco.editor.getModel(t);o&&o.getModeId()===n&&monaco.editor.setModelMarkers(o,n,a)})}).then(void 0,function(i){console.error(i)})},e}();function Pe(e){switch(e){case y.Error:return monaco.MarkerSeverity.Error;case y.Warning:return monaco.MarkerSeverity.Warning;case y.Information:return monaco.MarkerSeverity.Info;case y.Hint:return monaco.MarkerSeverity.Hint;default:return monaco.MarkerSeverity.Info}}function Re(e,t){var n=typeof t.code=="number"?String(t.code):t.code;return{severity:Pe(t.severity),startLineNumber:t.range.start.line+1,startColumn:t.range.start.character+1,endLineNumber:t.range.end.line+1,endColumn:t.range.end.character+1,message:t.message,code:n,source:t.source}}function Ee(e){if(e)return{character:e.column-1,line:e.lineNumber-1}}function Ae(e){if(e)return{start:{line:e.startLineNumber-1,character:e.startColumn-1},end:{line:e.endLineNumber-1,character:e.endColumn-1}}}function S(e){if(e)return new Ce(e.start.line+1,e.start.character+1,e.end.line+1,e.end.character+1)}function Fe(e){var t=monaco.languages.CompletionItemKind;switch(e){case v.Text:return t.Text;case v.Method:return t.Method;case v.Function:return t.Function;case v.Constructor:return t.Constructor;case v.Field:return t.Field;case v.Variable:return t.Variable;case v.Class:return t.Class;case v.Interface:return t.Interface;case v.Module:return t.Module;case v.Property:return t.Property;case v.Unit:return t.Unit;case v.Value:return t.Value;case v.Enum:return t.Enum;case v.Keyword:return t.Keyword;case v.Snippet:return t.Snippet;case v.Color:return t.Color;case v.File:return t.File;case v.Reference:return t.Reference}return t.Property}function P(e){if(e)return{range:S(e.range),text:e.newText}}var Le=function(){function e(t){this._worker=t}return Object.defineProperty(e.prototype,"triggerCharacters",{get:function(){return[" ",":"]},enumerable:!0,configurable:!0}),e.prototype.provideCompletionItems=function(t,n,i,r){var a=t.uri;return this._worker(a).then(function(o){return o.doComplete(a.toString(),Ee(n))}).then(function(o){if(o){var u=t.getWordUntilPosition(n),d=new Ce(n.lineNumber,u.startColumn,n.lineNumber,u.endColumn),l=o.items.map(function(c){var h={label:c.label,insertText:c.insertText||c.label,sortText:c.sortText,filterText:c.filterText,documentation:c.documentation,detail:c.detail,range:d,kind:Fe(c.kind)};return c.textEdit&&(h.range=S(c.textEdit.range),h.insertText=c.textEdit.newText),c.additionalTextEdits&&(h.additionalTextEdits=c.additionalTextEdits.map(P)),c.insertTextFormat===Y.Snippet&&(h.insertTextRules=monaco.languages.CompletionItemInsertTextRule.InsertAsSnippet),h});return{isIncomplete:o.isIncomplete,suggestions:l}}})},e}();function Ne(e){return e&&typeof e=="object"&&typeof e.kind=="string"}function ke(e){return typeof e=="string"?{value:e}:Ne(e)?e.kind==="plaintext"?{value:e.value.replace(/[\\`*_{}[\]()#+\-.!]/g,"\\$&")}:{value:e.value}:{value:"```"+e.language+`
`+e.value+"\n```\n"}}function xe(e){if(e)return Array.isArray(e)?e.map(ke):[ke(e)]}var De=function(){function e(t){this._worker=t}return e.prototype.provideHover=function(t,n,i){var r=t.uri;return this._worker(r).then(function(a){return a.doHover(r.toString(),Ee(n))}).then(function(a){if(a)return{range:S(a.range),contents:xe(a.contents)}})},e}();function je(e){var t=monaco.languages.SymbolKind;switch(e){case p.File:return t.Array;case p.Module:return t.Module;case p.Namespace:return t.Namespace;case p.Package:return t.Package;case p.Class:return t.Class;case p.Method:return t.Method;case p.Property:return t.Property;case p.Field:return t.Field;case p.Constructor:return t.Constructor;case p.Enum:return t.Enum;case p.Interface:return t.Interface;case p.Function:return t.Function;case p.Variable:return t.Variable;case p.Constant:return t.Constant;case p.String:return t.String;case p.Number:return t.Number;case p.Boolean:return t.Boolean;case p.Array:return t.Array}return t.Function}var We=function(){function e(t){this._worker=t}return e.prototype.provideDocumentSymbols=function(t,n){var i=t.uri;return this._worker(i).then(function(r){return r.findDocumentSymbols(i.toString())}).then(function(r){if(r)return r.map(function(a){return{name:a.name,detail:"",containerName:a.containerName,kind:je(a.kind),range:S(a.location.range),selectionRange:S(a.location.range)}})})},e}();function Te(e){return{tabSize:e.tabSize,insertSpaces:e.insertSpaces}}var Ue=function(){function e(t){this._worker=t}return e.prototype.provideDocumentFormattingEdits=function(t,n,i){var r=t.uri;return this._worker(r).then(function(a){return a.format(r.toString(),null,Te(n)).then(function(o){if(!(!o||o.length===0))return o.map(P)})})},e}(),Ve=function(){function e(t){this._worker=t}return e.prototype.provideDocumentRangeFormattingEdits=function(t,n,i,r){var a=t.uri;return this._worker(a).then(function(o){return o.format(a.toString(),Ae(n),Te(i)).then(function(u){if(!(!u||u.length===0))return u.map(P)})})},e}(),He=function(){function e(t){this._worker=t}return e.prototype.provideDocumentColors=function(t,n){var i=t.uri;return this._worker(i).then(function(r){return r.findDocumentColors(i.toString())}).then(function(r){if(r)return r.map(function(a){return{color:a.color,range:S(a.range)}})})},e.prototype.provideColorPresentations=function(t,n,i){var r=t.uri;return this._worker(r).then(function(a){return a.getColorPresentations(r.toString(),n.color,Ae(n.range))}).then(function(a){if(a)return a.map(function(o){var u={label:o.label};return o.textEdit&&(u.textEdit=P(o.textEdit)),o.additionalTextEdits&&(u.additionalTextEdits=o.additionalTextEdits.map(P)),u})})},e}(),ze=function(){function e(t){this._worker=t}return e.prototype.provideFoldingRanges=function(t,n,i){var r=t.uri;return this._worker(r).then(function(a){return a.provideFoldingRanges(r.toString(),n)}).then(function(a){if(a)return a.map(function(o){var u={start:o.startLine+1,end:o.endLine+1};return typeof o.kind<"u"&&(u.kind=Be(o.kind)),u})})},e}();function Be(e){switch(e){case M.Comment:return monaco.languages.FoldingRangeKind.Comment;case M.Imports:return monaco.languages.FoldingRangeKind.Imports;case M.Region:return monaco.languages.FoldingRangeKind.Region}}function $e(e,t){t===void 0&&(t=!1);var n=0,i=e.length,r="",a=0,o=16,u=0;function d(f,m){for(var _=0,k=0;_<f||!m;){var w=e.charCodeAt(n);if(w>=48&&w<=57)k=k*16+w-48;else if(w>=65&&w<=70)k=k*16+w-65+10;else if(w>=97&&w<=102)k=k*16+w-97+10;else break;n++,_++}return _<f&&(k=-1),k}function l(f){n=f,r="",a=0,o=16,u=0}function c(){var f=n;if(e.charCodeAt(n)===48)n++;else for(n++;n<e.length&&T(e.charCodeAt(n));)n++;if(n<e.length&&e.charCodeAt(n)===46)if(n++,n<e.length&&T(e.charCodeAt(n)))for(n++;n<e.length&&T(e.charCodeAt(n));)n++;else return u=3,e.substring(f,n);var m=n;if(n<e.length&&(e.charCodeAt(n)===69||e.charCodeAt(n)===101))if(n++,(n<e.length&&e.charCodeAt(n)===43||e.charCodeAt(n)===45)&&n++,n<e.length&&T(e.charCodeAt(n))){for(n++;n<e.length&&T(e.charCodeAt(n));)n++;m=n}else u=3;return e.substring(f,m)}function h(){for(var f="",m=n;;){if(n>=i){f+=e.substring(m,n),u=2;break}var _=e.charCodeAt(n);if(_===34){f+=e.substring(m,n),n++;break}if(_===92){if(f+=e.substring(m,n),n++,n>=i){u=2;break}switch(_=e.charCodeAt(n++),_){case 34:f+='"';break;case 92:f+="\\";break;case 47:f+="/";break;case 98:f+="\b";break;case 102:f+="\f";break;case 110:f+=`
`;break;case 114:f+="\r";break;case 116:f+="	";break;case 117:var k=d(4,!0);k>=0?f+=String.fromCharCode(k):u=4;break;default:u=5}m=n;continue}if(_>=0&&_<=31)if(L(_)){f+=e.substring(m,n),u=2;break}else u=6;n++}return f}function g(){if(r="",u=0,a=n,n>=i)return a=i,o=17;var f=e.charCodeAt(n);if(H(f)){do n++,r+=String.fromCharCode(f),f=e.charCodeAt(n);while(H(f));return o=15}if(L(f))return n++,r+=String.fromCharCode(f),f===13&&e.charCodeAt(n)===10&&(n++,r+=`
`),o=14;switch(f){case 123:return n++,o=1;case 125:return n++,o=2;case 91:return n++,o=3;case 93:return n++,o=4;case 58:return n++,o=6;case 44:return n++,o=5;case 34:return n++,r=h(),o=10;case 47:var m=n-1;if(e.charCodeAt(n+1)===47){for(n+=2;n<i&&!L(e.charCodeAt(n));)n++;return r=e.substring(m,n),o=12}if(e.charCodeAt(n+1)===42){n+=2;for(var _=i-1,k=!1;n<_;){var w=e.charCodeAt(n);if(w===42&&e.charCodeAt(n+1)===47){n+=2,k=!0;break}n++}return k||(n++,u=1),r=e.substring(m,n),o=13}return r+=String.fromCharCode(f),n++,o=16;case 45:if(r+=String.fromCharCode(f),n++,n===i||!T(e.charCodeAt(n)))return o=16;case 48:case 49:case 50:case 51:case 52:case 53:case 54:case 55:case 56:case 57:return r+=c(),o=11;default:for(;n<i&&A(f);)n++,f=e.charCodeAt(n);if(a!==n){switch(r=e.substring(a,n),r){case"true":return o=8;case"false":return o=9;case"null":return o=7}return o=16}return r+=String.fromCharCode(f),n++,o=16}}function A(f){if(H(f)||L(f))return!1;switch(f){case 125:case 93:case 123:case 91:case 34:case 58:case 44:case 47:return!1}return!0}function R(){var f;do f=g();while(f>=12&&f<=15);return f}return{setPosition:l,getPosition:function(){return n},scan:t?R:g,getToken:function(){return o},getTokenValue:function(){return r},getTokenOffset:function(){return a},getTokenLength:function(){return n-a},getTokenError:function(){return u}}}function H(e){return e===32||e===9||e===11||e===12||e===160||e===5760||e>=8192&&e<=8203||e===8239||e===8287||e===12288||e===65279}function L(e){return e===10||e===13||e===8232||e===8233}function T(e){return e>=48&&e<=57}var be;(function(e){e.DEFAULT={allowTrailingComma:!1}})(be||(be={}));var qe=$e;function Je(e){return{getInitialState:function(){return new ye(null,null,!1)},tokenize:function(t,n,i,r){return nr(e,t,n,i)}}}var _e="delimiter.bracket.json",we="delimiter.array.json",Ye="delimiter.colon.json",Ge="delimiter.comma.json",Qe="keyword.json",Xe="keyword.json",Ze="string.value.json",Ke="number.json",er="string.key.json",rr="comment.block.json",tr="comment.line.json",ye=function(){function e(t,n,i){this._state=t,this.scanError=n,this.lastWasColon=i}return e.prototype.clone=function(){return new e(this._state,this.scanError,this.lastWasColon)},e.prototype.equals=function(t){return t===this?!0:!t||!(t instanceof e)?!1:this.scanError===t.scanError&&this.lastWasColon===t.lastWasColon},e.prototype.getStateData=function(){return this._state},e.prototype.setStateData=function(t){this._state=t},e}();function nr(e,t,n,i,r){i===void 0&&(i=0);var a=0,o=!1;switch(n.scanError){case 2:t='"'+t,a=1;break;case 1:t="/*"+t,a=2;break}var u=qe(t),d,l,c=n.lastWasColon;for(l={tokens:[],endState:n.clone()};;){var h=i+u.getPosition(),g="";if(d=u.scan(),d===17)break;if(h===i+u.getPosition())throw new Error("Scanner did not advance, next 3 characters are: "+t.substr(u.getPosition(),3));switch(o&&(h-=a),o=a>0,d){case 1:g=_e,c=!1;break;case 2:g=_e,c=!1;break;case 3:g=we,c=!1;break;case 4:g=we,c=!1;break;case 6:g=Ye,c=!0;break;case 5:g=Ge,c=!1;break;case 8:case 9:g=Qe,c=!1;break;case 7:g=Xe,c=!1;break;case 10:g=c?Ze:er,c=!1;break;case 11:g=Ke,c=!1;break}if(e)switch(d){case 12:g=tr;break;case 13:g=rr;break}l.endState=new ye(n.getStateData(),u.getTokenError(),c),l.tokens.push({startIndex:h,scopes:g})}return l}function ar(e){var t=[],n=new Me(e);t.push(n);var i=function(){for(var a=[],o=0;o<arguments.length;o++)a[o]=arguments[o];return n.getLanguageServiceWorker.apply(n,a)},r=e.languageId;t.push(monaco.languages.registerCompletionItemProvider(r,new Le(i))),t.push(monaco.languages.registerHoverProvider(r,new De(i))),t.push(monaco.languages.registerDocumentSymbolProvider(r,new We(i))),t.push(monaco.languages.registerDocumentFormattingEditProvider(r,new Ue(i))),t.push(monaco.languages.registerDocumentRangeFormattingEditProvider(r,new Ve(i))),t.push(new Ie(r,i,e)),t.push(monaco.languages.setTokensProvider(r,Je(!0))),t.push(monaco.languages.setLanguageConfiguration(r,ir)),t.push(monaco.languages.registerColorProvider(r,new He(i))),t.push(monaco.languages.registerFoldingRangeProvider(r,new ze(i)))}var ir={wordPattern:/(-?\d*\.\d\w*)|([^\[\{\]\}\:\"\,\s]+)/g,comments:{lineComment:"//",blockComment:["/*","*/"]},brackets:[["{","}"],["[","]"]],autoClosingPairs:[{open:"{",close:"}",notIn:["string"]},{open:"[",close:"]",notIn:["string"]},{open:'"',close:'"',notIn:["string"]}]};export{ar as setupMode};
