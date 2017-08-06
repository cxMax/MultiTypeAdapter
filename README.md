## MultiTypeAdapter 1.0

### 介绍
主要是为了简化具有多个ItemType的RecyclerView.Adapter的构建代码,不用在Adapter里面搞一堆ViewType,导致整个Adapter很臃肿.

### 缺点
现有代码的缺点 :   
* 仅因为Data<T> 而去继承MultiTypeAdapter, 假如adapter拥有多类型的数据,会导致类臃肿与继承层级变深,不利于后期维护.
* 保持RecyclerView.Adapter原有ViewType去注册ViewHolder, 但未体现面向对象的设计思想. 可以优化为data -> viewholder 的绑定
* 对于相同数据体(data)对应多个ViewHolder代码不够简洁, 需要多次注册, 无法实现一次性注册
* MultiTypeAdapter的设计过度依赖于继承实现, 可以完全保持其独立性, 并且很纯净, 不会去实现多余的接口方法, 避免在拓展MultiTypeAdapter的其他模块出现冲突, 例如 : header/footer以及load more,refresh,pinned header. 

### 使用方式
跟Popup一样,这个的使用也只需要关心五个步骤:
1. 创建<b>JavaBean类</b> :  ViewHolder的数据源
2. 创建<b>ViewHolder类</b> :   
                        a.在constructor里面初始化view;   
                        b.在update()方法里面,实现view更新;   
                        c.可以继承BaseVH  
3. 创建<b>Provider类</b> :   
                        <b>需继承AbsItemProvider</b>, AbsItemProvider具有于RecyclerView.Adapter相同的生命周期函数  
                        主要目的是实现ViewHolder与javabean的绑定.   
                        一般来说, 只需要override,onCreateViewHolder()和onBindViewHolder()即可,其他生命周期函数不用关心  
                        当然你还需要override,isForViewType(),这个是来判定provider持有的data是同一个viewtype的 
4. 创建<b>Adapter类</b> :  
                        <b>需继承MultiTypeAdapter</b>,
                        一般来说,只需要override,registerAllProvider()和updateData(), 
                        ItemViewType可以在<b>ViewTypeConstant</b>中定义  
                        当然,对应具体的业务或者参数需要传递到viewholder,可以重写onCreateViewHolder()或onBindViewHolder(),具体参考GameCouponPinnedAdapter
5. 最后在Activity或者Fragment中调用RecyclerView.setAdapter即可.

### 关于RecyclerView常用到的header/footer以及load more,refresh,pinned header等
1. 考虑到MultiTypeAdapter主要是为了简化多个ItemType构建,额外的header和footer可以通过Decorator来辅助实现,而不需要在现有的MultiTypeAdapter中进行增加ViewType
2. 以上的功能均可以通过一个单独的component来实现,具体参考MzRecyclerView

### 类关系
MultiTypeAdapter :　一个Adapter会持有一个MultiTypePool,对拥有的children进行注册．  
MultiTypePool　：　将不同ViewType的Provider保存在一个SparseArrayCompat里面,在对应Adapter的生命周期里面调用create和bind等相关方法.   
AbsItemProvider : Adapter和ViewHolder的桥梁, 涉及到一些具体业务上的参数或者接口调用,都可以通过此来中转.  
ViewTypeConstant : 保存不同Adapter的ViewType,每一个Adapter都可以其中新增一个child.  
ViewHolder : 实现具体的item相关的视图改变,以及数据绑定.   

### Thanks  
1. sockeqwe / AdapterDelegates  
https://github.com/sockeqwe/AdapterDelegates  
2. hongyangAndroid / baseAdapter  
https://github.com/hongyangAndroid/baseAdapter  
3. drakeet/MultiType  
https://github.com/drakeet/MultiType  


### License
   Copyright (C) cxMax  
   Copyright (C) MultiTypeAdapter

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
