## MultiTypeAdapter 2.0

### 介绍
MultiTypeAdapter 2.0  
绝大部分代码来自[MultiType 3.x](https://github.com/drakeet/MultiType)
在了解了其设计原理以及代码技巧后,并且在使用过程中根据项目实际的业务需求进行修改.

### 优点
* 不得不说[MultiType](https://github.com/drakeet/MultiType)相比去之前自己写的[MultiTypeAdapter 1.0](https://github.com/cxMax/MultiTypeAdapter/tree/1.0), 整个设计确实很简洁, 方便Adapter在具体业务场景情况下进行拓展,不会有冗余继承代码.
* 在data使用方面, 使用通配符替代了泛型. 确实弥补了继承对于封装性的缺陷(仅仅因为data<T>去设计成继承, 确实成本太高).
* 在单个data对多个ItemViewBinder的转化实现,这部分代码确实很简洁, 受益匪浅.

### 变更
1. 在Adapter的onCreateViewHolder()和onBindViewHolder()函数保持了Recyclerview.Adapter对应函数参数postion的传递.
2. ViewHolder局部刷新使用DiffUtil配合payloads的实现. ps : 当然这一点对于MultiTypeAdapter没多大的关系

### Thanks  
1. sockeqwe / AdapterDelegates  
https://github.com/sockeqwe/AdapterDelegates  
2. hongyangAndroid / baseAdapter  
https://github.com/hongyangAndroid/baseAdapter  
3. drakeet/MultiType  
https://github.com/drakeet/MultiType  


### License
   Copyright (C) cxMax   
   Copyright (C) MultiTypeAdapter 2.0

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
