var pe=12e4,me=function(){function e(r){var i=this;this._defaults=r,this._worker=null,this._idleCheckInterval=setInterval(function(){return i._checkIfIdle()},30*1e3),this._lastUsedTime=0,this._configChangeListener=this._defaults.onDidChange(function(){return i._stopWorker()})}return e.prototype._stopWorker=function(){this._worker&&(this._worker.dispose(),this._worker=null),this._client=null},e.prototype.dispose=function(){clearInterval(this._idleCheckInterval),this._configChangeListener.dispose(),this._stopWorker()},e.prototype._checkIfIdle=function(){if(this._worker){var r=Date.now()-this._lastUsedTime;r>pe&&this._stopWorker()}},e.prototype._getClient=function(){return this._lastUsedTime=Date.now(),this._client||(this._worker=monaco.editor.createWebWorker({moduleId:"vs/language/css/cssWorker",label:this._defaults.languageId,createData:{languageSettings:this._defaults.diagnosticsOptions,languageId:this._defaults.languageId}}),this._client=this._worker.getProxy()),this._client},e.prototype.getLanguageServiceWorker=function(){for(var r=this,i=[],n=0;n<arguments.length;n++)i[n]=arguments[n];var t;return this._getClient().then(function(a){t=a}).then(function(a){return r._worker.withSyncedResources(i)}).then(function(a){return t})},e}(),p;(function(e){function r(n,t){return{line:n,character:t}}e.create=r;function i(n){var t=n;return o.objectLiteral(t)&&o.number(t.line)&&o.number(t.character)}e.is=i})(p||(p={}));var v;(function(e){function r(n,t,a,u){if(o.number(n)&&o.number(t)&&o.number(a)&&o.number(u))return{start:p.create(n,t),end:p.create(a,u)};if(p.is(n)&&p.is(t))return{start:n,end:t};throw new Error("Range#create called with invalid arguments["+n+", "+t+", "+a+", "+u+"]")}e.create=r;function i(n){var t=n;return o.objectLiteral(t)&&p.is(t.start)&&p.is(t.end)}e.is=i})(v||(v={}));var L;(function(e){function r(n,t){return{uri:n,range:t}}e.create=r;function i(n){var t=n;return o.defined(t)&&v.is(t.range)&&(o.string(t.uri)||o.undefined(t.uri))}e.is=i})(L||(L={}));var B;(function(e){function r(n,t,a,u){return{targetUri:n,targetRange:t,targetSelectionRange:a,originSelectionRange:u}}e.create=r;function i(n){var t=n;return o.defined(t)&&v.is(t.targetRange)&&o.string(t.targetUri)&&(v.is(t.targetSelectionRange)||o.undefined(t.targetSelectionRange))&&(v.is(t.originSelectionRange)||o.undefined(t.originSelectionRange))}e.is=i})(B||(B={}));var O;(function(e){function r(n,t,a,u){return{red:n,green:t,blue:a,alpha:u}}e.create=r;function i(n){var t=n;return o.number(t.red)&&o.number(t.green)&&o.number(t.blue)&&o.number(t.alpha)}e.is=i})(O||(O={}));var q;(function(e){function r(n,t){return{range:n,color:t}}e.create=r;function i(n){var t=n;return v.is(t.range)&&O.is(t.color)}e.is=i})(q||(q={}));var Q;(function(e){function r(n,t,a){return{label:n,textEdit:t,additionalTextEdits:a}}e.create=r;function i(n){var t=n;return o.string(t.label)&&(o.undefined(t.textEdit)||_.is(t))&&(o.undefined(t.additionalTextEdits)||o.typedArray(t.additionalTextEdits,_.is))}e.is=i})(Q||(Q={}));var x;(function(e){e.Comment="comment",e.Imports="imports",e.Region="region"})(x||(x={}));var G;(function(e){function r(n,t,a,u,c){var f={startLine:n,endLine:t};return o.defined(a)&&(f.startCharacter=a),o.defined(u)&&(f.endCharacter=u),o.defined(c)&&(f.kind=c),f}e.create=r;function i(n){var t=n;return o.number(t.startLine)&&o.number(t.startLine)&&(o.undefined(t.startCharacter)||o.number(t.startCharacter))&&(o.undefined(t.endCharacter)||o.number(t.endCharacter))&&(o.undefined(t.kind)||o.string(t.kind))}e.is=i})(G||(G={}));var W;(function(e){function r(n,t){return{location:n,message:t}}e.create=r;function i(n){var t=n;return o.defined(t)&&L.is(t.location)&&o.string(t.message)}e.is=i})(W||(W={}));var w;(function(e){e.Error=1,e.Warning=2,e.Information=3,e.Hint=4})(w||(w={}));var A;(function(e){function r(n,t,a,u,c,f){var d={range:n,message:t};return o.defined(a)&&(d.severity=a),o.defined(u)&&(d.code=u),o.defined(c)&&(d.source=c),o.defined(f)&&(d.relatedInformation=f),d}e.create=r;function i(n){var t=n;return o.defined(t)&&v.is(t.range)&&o.string(t.message)&&(o.number(t.severity)||o.undefined(t.severity))&&(o.number(t.code)||o.string(t.code)||o.undefined(t.code))&&(o.string(t.source)||o.undefined(t.source))&&(o.undefined(t.relatedInformation)||o.typedArray(t.relatedInformation,W.is))}e.is=i})(A||(A={}));var y;(function(e){function r(n,t){for(var a=[],u=2;u<arguments.length;u++)a[u-2]=arguments[u];var c={title:n,command:t};return o.defined(a)&&a.length>0&&(c.arguments=a),c}e.create=r;function i(n){var t=n;return o.defined(t)&&o.string(t.title)&&o.string(t.command)}e.is=i})(y||(y={}));var _;(function(e){function r(a,u){return{range:a,newText:u}}e.replace=r;function i(a,u){return{range:{start:a,end:a},newText:u}}e.insert=i;function n(a){return{range:a,newText:""}}e.del=n;function t(a){var u=a;return o.objectLiteral(u)&&o.string(u.newText)&&v.is(u.range)}e.is=t})(_||(_={}));var M;(function(e){function r(n,t){return{textDocument:n,edits:t}}e.create=r;function i(n){var t=n;return o.defined(t)&&F.is(t.textDocument)&&Array.isArray(t.edits)}e.is=i})(M||(M={}));var I;(function(e){function r(n,t){var a={kind:"create",uri:n};return t!==void 0&&(t.overwrite!==void 0||t.ignoreIfExists!==void 0)&&(a.options=t),a}e.create=r;function i(n){var t=n;return t&&t.kind==="create"&&o.string(t.uri)&&(t.options===void 0||(t.options.overwrite===void 0||o.boolean(t.options.overwrite))&&(t.options.ignoreIfExists===void 0||o.boolean(t.options.ignoreIfExists)))}e.is=i})(I||(I={}));var D;(function(e){function r(n,t,a){var u={kind:"rename",oldUri:n,newUri:t};return a!==void 0&&(a.overwrite!==void 0||a.ignoreIfExists!==void 0)&&(u.options=a),u}e.create=r;function i(n){var t=n;return t&&t.kind==="rename"&&o.string(t.oldUri)&&o.string(t.newUri)&&(t.options===void 0||(t.options.overwrite===void 0||o.boolean(t.options.overwrite))&&(t.options.ignoreIfExists===void 0||o.boolean(t.options.ignoreIfExists)))}e.is=i})(D||(D={}));var P;(function(e){function r(n,t){var a={kind:"delete",uri:n};return t!==void 0&&(t.recursive!==void 0||t.ignoreIfNotExists!==void 0)&&(a.options=t),a}e.create=r;function i(n){var t=n;return t&&t.kind==="delete"&&o.string(t.uri)&&(t.options===void 0||(t.options.recursive===void 0||o.boolean(t.options.recursive))&&(t.options.ignoreIfNotExists===void 0||o.boolean(t.options.ignoreIfNotExists)))}e.is=i})(P||(P={}));var j;(function(e){function r(i){var n=i;return n&&(n.changes!==void 0||n.documentChanges!==void 0)&&(n.documentChanges===void 0||n.documentChanges.every(function(t){return o.string(t.kind)?I.is(t)||D.is(t)||P.is(t):M.is(t)}))}e.is=r})(j||(j={}));var T=function(){function e(r){this.edits=r}return e.prototype.insert=function(r,i){this.edits.push(_.insert(r,i))},e.prototype.replace=function(r,i){this.edits.push(_.replace(r,i))},e.prototype.delete=function(r){this.edits.push(_.del(r))},e.prototype.add=function(r){this.edits.push(r)},e.prototype.all=function(){return this.edits},e.prototype.clear=function(){this.edits.splice(0,this.edits.length)},e}();(function(){function e(r){var i=this;this._textEditChanges=Object.create(null),r&&(this._workspaceEdit=r,r.documentChanges?r.documentChanges.forEach(function(n){if(M.is(n)){var t=new T(n.edits);i._textEditChanges[n.textDocument.uri]=t}}):r.changes&&Object.keys(r.changes).forEach(function(n){var t=new T(r.changes[n]);i._textEditChanges[n]=t}))}return Object.defineProperty(e.prototype,"edit",{get:function(){return this._workspaceEdit},enumerable:!0,configurable:!0}),e.prototype.getTextEditChange=function(r){if(F.is(r)){if(this._workspaceEdit||(this._workspaceEdit={documentChanges:[]}),!this._workspaceEdit.documentChanges)throw new Error("Workspace edit is not configured for document changes.");var i=r,n=this._textEditChanges[i.uri];if(!n){var t=[],a={textDocument:i,edits:t};this._workspaceEdit.documentChanges.push(a),n=new T(t),this._textEditChanges[i.uri]=n}return n}else{if(this._workspaceEdit||(this._workspaceEdit={changes:Object.create(null)}),!this._workspaceEdit.changes)throw new Error("Workspace edit is not configured for normal text edit changes.");var n=this._textEditChanges[r];if(!n){var t=[];this._workspaceEdit.changes[r]=t,n=new T(t),this._textEditChanges[r]=n}return n}},e.prototype.createFile=function(r,i){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(I.create(r,i))},e.prototype.renameFile=function(r,i,n){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(D.create(r,i,n))},e.prototype.deleteFile=function(r,i){this.checkDocumentChanges(),this._workspaceEdit.documentChanges.push(P.create(r,i))},e.prototype.checkDocumentChanges=function(){if(!this._workspaceEdit||!this._workspaceEdit.documentChanges)throw new Error("Workspace edit is not configured for document changes.")},e})();var J;(function(e){function r(n){return{uri:n}}e.create=r;function i(n){var t=n;return o.defined(t)&&o.string(t.uri)}e.is=i})(J||(J={}));var F;(function(e){function r(n,t){return{uri:n,version:t}}e.create=r;function i(n){var t=n;return o.defined(t)&&o.string(t.uri)&&(t.version===null||o.number(t.version))}e.is=i})(F||(F={}));var X;(function(e){function r(n,t,a,u){return{uri:n,languageId:t,version:a,text:u}}e.create=r;function i(n){var t=n;return o.defined(t)&&o.string(t.uri)&&o.string(t.languageId)&&o.number(t.version)&&o.string(t.text)}e.is=i})(X||(X={}));var C;(function(e){e.PlainText="plaintext",e.Markdown="markdown"})(C||(C={}));(function(e){function r(i){var n=i;return n===e.PlainText||n===e.Markdown}e.is=r})(C||(C={}));var N;(function(e){function r(i){var n=i;return o.objectLiteral(i)&&C.is(n.kind)&&o.string(n.value)}e.is=r})(N||(N={}));var l;(function(e){e.Text=1,e.Method=2,e.Function=3,e.Constructor=4,e.Field=5,e.Variable=6,e.Class=7,e.Interface=8,e.Module=9,e.Property=10,e.Unit=11,e.Value=12,e.Enum=13,e.Keyword=14,e.Snippet=15,e.Color=16,e.File=17,e.Reference=18,e.Folder=19,e.EnumMember=20,e.Constant=21,e.Struct=22,e.Event=23,e.Operator=24,e.TypeParameter=25})(l||(l={}));var H;(function(e){e.PlainText=1,e.Snippet=2})(H||(H={}));var Y;(function(e){function r(i){return{label:i}}e.create=r})(Y||(Y={}));var Z;(function(e){function r(i,n){return{items:i||[],isIncomplete:!!n}}e.create=r})(Z||(Z={}));var S;(function(e){function r(n){return n.replace(/[\\`*_{}[\]()#+\-.!]/g,"\\$&")}e.fromPlainText=r;function i(n){var t=n;return o.string(t)||o.objectLiteral(t)&&o.string(t.language)&&o.string(t.value)}e.is=i})(S||(S={}));var K;(function(e){function r(i){var n=i;return!!n&&o.objectLiteral(n)&&(N.is(n.contents)||S.is(n.contents)||o.typedArray(n.contents,S.is))&&(i.range===void 0||v.is(i.range))}e.is=r})(K||(K={}));var ee;(function(e){function r(i,n){return n?{label:i,documentation:n}:{label:i}}e.create=r})(ee||(ee={}));var te;(function(e){function r(i,n){for(var t=[],a=2;a<arguments.length;a++)t[a-2]=arguments[a];var u={label:i};return o.defined(n)&&(u.documentation=n),o.defined(t)?u.parameters=t:u.parameters=[],u}e.create=r})(te||(te={}));var E;(function(e){e.Text=1,e.Read=2,e.Write=3})(E||(E={}));var re;(function(e){function r(i,n){var t={range:i};return o.number(n)&&(t.kind=n),t}e.create=r})(re||(re={}));var g;(function(e){e.File=1,e.Module=2,e.Namespace=3,e.Package=4,e.Class=5,e.Method=6,e.Property=7,e.Field=8,e.Constructor=9,e.Enum=10,e.Interface=11,e.Function=12,e.Variable=13,e.Constant=14,e.String=15,e.Number=16,e.Boolean=17,e.Array=18,e.Object=19,e.Key=20,e.Null=21,e.EnumMember=22,e.Struct=23,e.Event=24,e.Operator=25,e.TypeParameter=26})(g||(g={}));var ne;(function(e){function r(i,n,t,a,u){var c={name:i,kind:n,location:{uri:a,range:t}};return u&&(c.containerName=u),c}e.create=r})(ne||(ne={}));var ie=function(){function e(){}return e}();(function(e){function r(n,t,a,u,c,f){var d={name:n,detail:t,kind:a,range:u,selectionRange:c};return f!==void 0&&(d.children=f),d}e.create=r;function i(n){var t=n;return t&&o.string(t.name)&&o.number(t.kind)&&v.is(t.range)&&v.is(t.selectionRange)&&(t.detail===void 0||o.string(t.detail))&&(t.deprecated===void 0||o.boolean(t.deprecated))&&(t.children===void 0||Array.isArray(t.children))}e.is=i})(ie||(ie={}));var ae;(function(e){e.QuickFix="quickfix",e.Refactor="refactor",e.RefactorExtract="refactor.extract",e.RefactorInline="refactor.inline",e.RefactorRewrite="refactor.rewrite",e.Source="source",e.SourceOrganizeImports="source.organizeImports"})(ae||(ae={}));var oe;(function(e){function r(n,t){var a={diagnostics:n};return t!=null&&(a.only=t),a}e.create=r;function i(n){var t=n;return o.defined(t)&&o.typedArray(t.diagnostics,A.is)&&(t.only===void 0||o.typedArray(t.only,o.string))}e.is=i})(oe||(oe={}));var ue;(function(e){function r(n,t,a){var u={title:n};return y.is(t)?u.command=t:u.edit=t,a!==void 0&&(u.kind=a),u}e.create=r;function i(n){var t=n;return t&&o.string(t.title)&&(t.diagnostics===void 0||o.typedArray(t.diagnostics,A.is))&&(t.kind===void 0||o.string(t.kind))&&(t.edit!==void 0||t.command!==void 0)&&(t.command===void 0||y.is(t.command))&&(t.edit===void 0||j.is(t.edit))}e.is=i})(ue||(ue={}));var ce;(function(e){function r(n,t){var a={range:n};return o.defined(t)&&(a.data=t),a}e.create=r;function i(n){var t=n;return o.defined(t)&&v.is(t.range)&&(o.undefined(t.command)||y.is(t.command))}e.is=i})(ce||(ce={}));var se;(function(e){function r(n,t){return{tabSize:n,insertSpaces:t}}e.create=r;function i(n){var t=n;return o.defined(t)&&o.number(t.tabSize)&&o.boolean(t.insertSpaces)}e.is=i})(se||(se={}));var fe=function(){function e(){}return e}();(function(e){function r(n,t,a){return{range:n,target:t,data:a}}e.create=r;function i(n){var t=n;return o.defined(t)&&v.is(t.range)&&(o.undefined(t.target)||o.string(t.target))}e.is=i})(fe||(fe={}));var de;(function(e){function r(a,u,c,f){return new ke(a,u,c,f)}e.create=r;function i(a){var u=a;return!!(o.defined(u)&&o.string(u.uri)&&(o.undefined(u.languageId)||o.string(u.languageId))&&o.number(u.lineCount)&&o.func(u.getText)&&o.func(u.positionAt)&&o.func(u.offsetAt))}e.is=i;function n(a,u){for(var c=a.getText(),f=t(u,function(V,z){var $=V.range.start.line-z.range.start.line;return $===0?V.range.start.character-z.range.start.character:$}),d=c.length,s=f.length-1;s>=0;s--){var h=f[s],k=a.offsetAt(h.range.start),R=a.offsetAt(h.range.end);if(R<=d)c=c.substring(0,k)+h.newText+c.substring(R,c.length);else throw new Error("Overlapping edit");d=k}return c}e.applyEdits=n;function t(a,u){if(a.length<=1)return a;var c=a.length/2|0,f=a.slice(0,c),d=a.slice(c);t(f,u),t(d,u);for(var s=0,h=0,k=0;s<f.length&&h<d.length;){var R=u(f[s],d[h]);R<=0?a[k++]=f[s++]:a[k++]=d[h++]}for(;s<f.length;)a[k++]=f[s++];for(;h<d.length;)a[k++]=d[h++];return a}})(de||(de={}));var le;(function(e){e.Manual=1,e.AfterDelay=2,e.FocusOut=3})(le||(le={}));var ke=function(){function e(r,i,n,t){this._uri=r,this._languageId=i,this._version=n,this._content=t,this._lineOffsets=null}return Object.defineProperty(e.prototype,"uri",{get:function(){return this._uri},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"languageId",{get:function(){return this._languageId},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"version",{get:function(){return this._version},enumerable:!0,configurable:!0}),e.prototype.getText=function(r){if(r){var i=this.offsetAt(r.start),n=this.offsetAt(r.end);return this._content.substring(i,n)}return this._content},e.prototype.update=function(r,i){this._content=r.text,this._version=i,this._lineOffsets=null},e.prototype.getLineOffsets=function(){if(this._lineOffsets===null){for(var r=[],i=this._content,n=!0,t=0;t<i.length;t++){n&&(r.push(t),n=!1);var a=i.charAt(t);n=a==="\r"||a===`
`,a==="\r"&&t+1<i.length&&i.charAt(t+1)===`
`&&t++}n&&i.length>0&&r.push(i.length),this._lineOffsets=r}return this._lineOffsets},e.prototype.positionAt=function(r){r=Math.max(Math.min(r,this._content.length),0);var i=this.getLineOffsets(),n=0,t=i.length;if(t===0)return p.create(0,r);for(;n<t;){var a=Math.floor((n+t)/2);i[a]>r?t=a:n=a+1}var u=n-1;return p.create(u,r-i[u])},e.prototype.offsetAt=function(r){var i=this.getLineOffsets();if(r.line>=i.length)return this._content.length;if(r.line<0)return 0;var n=i[r.line],t=r.line+1<i.length?i[r.line+1]:this._content.length;return Math.max(Math.min(n+r.character,t),n)},Object.defineProperty(e.prototype,"lineCount",{get:function(){return this.getLineOffsets().length},enumerable:!0,configurable:!0}),e}(),o;(function(e){var r=Object.prototype.toString;function i(s){return typeof s<"u"}e.defined=i;function n(s){return typeof s>"u"}e.undefined=n;function t(s){return s===!0||s===!1}e.boolean=t;function a(s){return r.call(s)==="[object String]"}e.string=a;function u(s){return r.call(s)==="[object Number]"}e.number=u;function c(s){return r.call(s)==="[object Function]"}e.func=c;function f(s){return s!==null&&typeof s=="object"}e.objectLiteral=f;function d(s,h){return Array.isArray(s)&&s.every(h)}e.typedArray=d})(o||(o={}));var he=monaco.Uri,_e=monaco.Range,we=function(){function e(r,i,n){var t=this;this._languageId=r,this._worker=i,this._disposables=[],this._listener=Object.create(null);var a=function(c){var f=c.getModeId();if(f===t._languageId){var d;t._listener[c.uri.toString()]=c.onDidChangeContent(function(){clearTimeout(d),d=setTimeout(function(){return t._doValidate(c.uri,f)},500)}),t._doValidate(c.uri,f)}},u=function(c){monaco.editor.setModelMarkers(c,t._languageId,[]);var f=c.uri.toString(),d=t._listener[f];d&&(d.dispose(),delete t._listener[f])};this._disposables.push(monaco.editor.onDidCreateModel(a)),this._disposables.push(monaco.editor.onWillDisposeModel(u)),this._disposables.push(monaco.editor.onDidChangeModelLanguage(function(c){u(c.model),a(c.model)})),n.onDidChange(function(c){monaco.editor.getModels().forEach(function(f){f.getModeId()===t._languageId&&(u(f),a(f))})}),this._disposables.push({dispose:function(){for(var c in t._listener)t._listener[c].dispose()}}),monaco.editor.getModels().forEach(a)}return e.prototype.dispose=function(){this._disposables.forEach(function(r){return r&&r.dispose()}),this._disposables=[]},e.prototype._doValidate=function(r,i){this._worker(r).then(function(n){return n.doValidation(r.toString())}).then(function(n){var t=n.map(function(u){return xe(r,u)}),a=monaco.editor.getModel(r);a.getModeId()===i&&monaco.editor.setModelMarkers(a,i,t)}).then(void 0,function(n){console.error(n)})},e}();function be(e){switch(e){case w.Error:return monaco.MarkerSeverity.Error;case w.Warning:return monaco.MarkerSeverity.Warning;case w.Information:return monaco.MarkerSeverity.Info;case w.Hint:return monaco.MarkerSeverity.Hint;default:return monaco.MarkerSeverity.Info}}function xe(e,r){var i=typeof r.code=="number"?String(r.code):r.code;return{severity:be(r.severity),startLineNumber:r.range.start.line+1,startColumn:r.range.start.character+1,endLineNumber:r.range.end.line+1,endColumn:r.range.end.character+1,message:r.message,code:i,source:r.source}}function b(e){if(e)return{character:e.column-1,line:e.lineNumber-1}}function Ee(e){if(e)return{start:{line:e.startLineNumber-1,character:e.startColumn-1},end:{line:e.endLineNumber-1,character:e.endColumn-1}}}function m(e){if(e)return new monaco.Range(e.start.line+1,e.start.character+1,e.end.line+1,e.end.character+1)}function ye(e){var r=monaco.languages.CompletionItemKind;switch(e){case l.Text:return r.Text;case l.Method:return r.Method;case l.Function:return r.Function;case l.Constructor:return r.Constructor;case l.Field:return r.Field;case l.Variable:return r.Variable;case l.Class:return r.Class;case l.Interface:return r.Interface;case l.Module:return r.Module;case l.Property:return r.Property;case l.Unit:return r.Unit;case l.Value:return r.Value;case l.Enum:return r.Enum;case l.Keyword:return r.Keyword;case l.Snippet:return r.Snippet;case l.Color:return r.Color;case l.File:return r.File;case l.Reference:return r.Reference}return r.Property}function U(e){if(e)return{range:m(e.range),text:e.newText}}var Ce=function(){function e(r){this._worker=r}return Object.defineProperty(e.prototype,"triggerCharacters",{get:function(){return[" ",":"]},enumerable:!0,configurable:!0}),e.prototype.provideCompletionItems=function(r,i,n,t){var a=r.uri;return this._worker(a).then(function(u){return u.doComplete(a.toString(),b(i))}).then(function(u){if(u){var c=r.getWordUntilPosition(i),f=new _e(i.lineNumber,c.startColumn,i.lineNumber,c.endColumn),d=u.items.map(function(s){var h={label:s.label,insertText:s.insertText||s.label,sortText:s.sortText,filterText:s.filterText,documentation:s.documentation,detail:s.detail,range:f,kind:ye(s.kind)};return s.textEdit&&(h.range=m(s.textEdit.range),h.insertText=s.textEdit.newText),s.additionalTextEdits&&(h.additionalTextEdits=s.additionalTextEdits.map(U)),s.insertTextFormat===H.Snippet&&(h.insertTextRules=monaco.languages.CompletionItemInsertTextRule.InsertAsSnippet),h});return{isIncomplete:u.isIncomplete,suggestions:d}}})},e}();function Re(e){return e&&typeof e=="object"&&typeof e.kind=="string"}function ge(e){return typeof e=="string"?{value:e}:Re(e)?e.kind==="plaintext"?{value:e.value.replace(/[\\`*_{}[\]()#+\-.!]/g,"\\$&")}:{value:e.value}:{value:"```"+e.language+`
`+e.value+"\n```\n"}}function Te(e){if(e)return Array.isArray(e)?e.map(ge):[ge(e)]}var Ae=function(){function e(r){this._worker=r}return e.prototype.provideHover=function(r,i,n){var t=r.uri;return this._worker(t).then(function(a){return a.doHover(t.toString(),b(i))}).then(function(a){if(a)return{range:m(a.range),contents:Te(a.contents)}})},e}();function Me(e){switch(e){case E.Read:return monaco.languages.DocumentHighlightKind.Read;case E.Write:return monaco.languages.DocumentHighlightKind.Write;case E.Text:return monaco.languages.DocumentHighlightKind.Text}return monaco.languages.DocumentHighlightKind.Text}var Ie=function(){function e(r){this._worker=r}return e.prototype.provideDocumentHighlights=function(r,i,n){var t=r.uri;return this._worker(t).then(function(a){return a.findDocumentHighlights(t.toString(),b(i))}).then(function(a){if(a)return a.map(function(u){return{range:m(u.range),kind:Me(u.kind)}})})},e}();function ve(e){return{uri:he.parse(e.uri),range:m(e.range)}}var De=function(){function e(r){this._worker=r}return e.prototype.provideDefinition=function(r,i,n){var t=r.uri;return this._worker(t).then(function(a){return a.findDefinition(t.toString(),b(i))}).then(function(a){if(a)return[ve(a)]})},e}(),Pe=function(){function e(r){this._worker=r}return e.prototype.provideReferences=function(r,i,n,t){var a=r.uri;return this._worker(a).then(function(u){return u.findReferences(a.toString(),b(i))}).then(function(u){if(u)return u.map(ve)})},e}();function Fe(e){if(!(!e||!e.changes)){var r=[];for(var i in e.changes){for(var n=[],t=0,a=e.changes[i];t<a.length;t++){var u=a[t];n.push({range:m(u.range),text:u.newText})}r.push({resource:he.parse(i),edits:n})}return{edits:r}}}var Se=function(){function e(r){this._worker=r}return e.prototype.provideRenameEdits=function(r,i,n,t){var a=r.uri;return this._worker(a).then(function(u){return u.doRename(a.toString(),b(i),n)}).then(function(u){return Fe(u)})},e}();function Le(e){var r=monaco.languages.SymbolKind;switch(e){case g.File:return r.Array;case g.Module:return r.Module;case g.Namespace:return r.Namespace;case g.Package:return r.Package;case g.Class:return r.Class;case g.Method:return r.Method;case g.Property:return r.Property;case g.Field:return r.Field;case g.Constructor:return r.Constructor;case g.Enum:return r.Enum;case g.Interface:return r.Interface;case g.Function:return r.Function;case g.Variable:return r.Variable;case g.Constant:return r.Constant;case g.String:return r.String;case g.Number:return r.Number;case g.Boolean:return r.Boolean;case g.Array:return r.Array}return r.Function}var Oe=function(){function e(r){this._worker=r}return e.prototype.provideDocumentSymbols=function(r,i){var n=r.uri;return this._worker(n).then(function(t){return t.findDocumentSymbols(n.toString())}).then(function(t){if(t)return t.map(function(a){return{name:a.name,detail:"",containerName:a.containerName,kind:Le(a.kind),range:m(a.location.range),selectionRange:m(a.location.range)}})})},e}(),We=function(){function e(r){this._worker=r}return e.prototype.provideDocumentColors=function(r,i){var n=r.uri;return this._worker(n).then(function(t){return t.findDocumentColors(n.toString())}).then(function(t){if(t)return t.map(function(a){return{color:a.color,range:m(a.range)}})})},e.prototype.provideColorPresentations=function(r,i,n){var t=r.uri;return this._worker(t).then(function(a){return a.getColorPresentations(t.toString(),i.color,Ee(i.range))}).then(function(a){if(a)return a.map(function(u){var c={label:u.label};return u.textEdit&&(c.textEdit=U(u.textEdit)),u.additionalTextEdits&&(c.additionalTextEdits=u.additionalTextEdits.map(U)),c})})},e}(),je=function(){function e(r){this._worker=r}return e.prototype.provideFoldingRanges=function(r,i,n){var t=r.uri;return this._worker(t).then(function(a){return a.provideFoldingRanges(t.toString(),i)}).then(function(a){if(a)return a.map(function(u){var c={start:u.startLine+1,end:u.endLine+1};return typeof u.kind<"u"&&(c.kind=Ne(u.kind)),c})})},e}();function Ne(e){switch(e){case x.Comment:return monaco.languages.FoldingRangeKind.Comment;case x.Imports:return monaco.languages.FoldingRangeKind.Imports;case x.Region:return monaco.languages.FoldingRangeKind.Region}}function He(e){var r=new me(e),i=function(t){for(var a=[],u=1;u<arguments.length;u++)a[u-1]=arguments[u];return r.getLanguageServiceWorker.apply(r,[t].concat(a))},n=e.languageId;monaco.languages.registerCompletionItemProvider(n,new Ce(i)),monaco.languages.registerHoverProvider(n,new Ae(i)),monaco.languages.registerDocumentHighlightProvider(n,new Ie(i)),monaco.languages.registerDefinitionProvider(n,new De(i)),monaco.languages.registerReferenceProvider(n,new Pe(i)),monaco.languages.registerDocumentSymbolProvider(n,new Oe(i)),monaco.languages.registerRenameProvider(n,new Se(i)),monaco.languages.registerColorProvider(n,new We(i)),monaco.languages.registerFoldingRangeProvider(n,new je(i)),new we(n,i,e)}export{He as setupMode};
