// Compiled by ClojureScript 1.7.122 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4425__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4425__auto__)){
var ns = temp__4425__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__7599_7613 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__7600_7614 = null;
var count__7601_7615 = (0);
var i__7602_7616 = (0);
while(true){
if((i__7602_7616 < count__7601_7615)){
var f_7617 = cljs.core._nth.call(null,chunk__7600_7614,i__7602_7616);
cljs.core.println.call(null,"  ",f_7617);

var G__7618 = seq__7599_7613;
var G__7619 = chunk__7600_7614;
var G__7620 = count__7601_7615;
var G__7621 = (i__7602_7616 + (1));
seq__7599_7613 = G__7618;
chunk__7600_7614 = G__7619;
count__7601_7615 = G__7620;
i__7602_7616 = G__7621;
continue;
} else {
var temp__4425__auto___7622 = cljs.core.seq.call(null,seq__7599_7613);
if(temp__4425__auto___7622){
var seq__7599_7623__$1 = temp__4425__auto___7622;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7599_7623__$1)){
var c__7246__auto___7624 = cljs.core.chunk_first.call(null,seq__7599_7623__$1);
var G__7625 = cljs.core.chunk_rest.call(null,seq__7599_7623__$1);
var G__7626 = c__7246__auto___7624;
var G__7627 = cljs.core.count.call(null,c__7246__auto___7624);
var G__7628 = (0);
seq__7599_7613 = G__7625;
chunk__7600_7614 = G__7626;
count__7601_7615 = G__7627;
i__7602_7616 = G__7628;
continue;
} else {
var f_7629 = cljs.core.first.call(null,seq__7599_7623__$1);
cljs.core.println.call(null,"  ",f_7629);

var G__7630 = cljs.core.next.call(null,seq__7599_7623__$1);
var G__7631 = null;
var G__7632 = (0);
var G__7633 = (0);
seq__7599_7613 = G__7630;
chunk__7600_7614 = G__7631;
count__7601_7615 = G__7632;
i__7602_7616 = G__7633;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_7634 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__6443__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__6443__auto__)){
return or__6443__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_7634);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_7634)))?cljs.core.second.call(null,arglists_7634):arglists_7634));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__7603 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__7604 = null;
var count__7605 = (0);
var i__7606 = (0);
while(true){
if((i__7606 < count__7605)){
var vec__7607 = cljs.core._nth.call(null,chunk__7604,i__7606);
var name = cljs.core.nth.call(null,vec__7607,(0),null);
var map__7608 = cljs.core.nth.call(null,vec__7607,(1),null);
var map__7608__$1 = ((((!((map__7608 == null)))?((((map__7608.cljs$lang$protocol_mask$partition0$ & (64))) || (map__7608.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__7608):map__7608);
var doc = cljs.core.get.call(null,map__7608__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__7608__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__7635 = seq__7603;
var G__7636 = chunk__7604;
var G__7637 = count__7605;
var G__7638 = (i__7606 + (1));
seq__7603 = G__7635;
chunk__7604 = G__7636;
count__7605 = G__7637;
i__7606 = G__7638;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__7603);
if(temp__4425__auto__){
var seq__7603__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7603__$1)){
var c__7246__auto__ = cljs.core.chunk_first.call(null,seq__7603__$1);
var G__7639 = cljs.core.chunk_rest.call(null,seq__7603__$1);
var G__7640 = c__7246__auto__;
var G__7641 = cljs.core.count.call(null,c__7246__auto__);
var G__7642 = (0);
seq__7603 = G__7639;
chunk__7604 = G__7640;
count__7605 = G__7641;
i__7606 = G__7642;
continue;
} else {
var vec__7610 = cljs.core.first.call(null,seq__7603__$1);
var name = cljs.core.nth.call(null,vec__7610,(0),null);
var map__7611 = cljs.core.nth.call(null,vec__7610,(1),null);
var map__7611__$1 = ((((!((map__7611 == null)))?((((map__7611.cljs$lang$protocol_mask$partition0$ & (64))) || (map__7611.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__7611):map__7611);
var doc = cljs.core.get.call(null,map__7611__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__7611__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__7643 = cljs.core.next.call(null,seq__7603__$1);
var G__7644 = null;
var G__7645 = (0);
var G__7646 = (0);
seq__7603 = G__7643;
chunk__7604 = G__7644;
count__7605 = G__7645;
i__7606 = G__7646;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map