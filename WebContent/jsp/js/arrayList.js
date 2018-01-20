//ArrayList 구현
function ArrayList(){
    this.array = new Array();
    this.add = function(obj){
        this.array[this.array.length] = obj;
    };
    this.iterator = function (){
        return new Iterator(this);
    };
    this.length = function (){
        return this.array.length;
    };
    this.get = function (index){
        return this.array[index];
    };
    this.addAll = function (obj){
        if (obj instanceof Array){
            for (var i=0;i<obj.length;i++){
                this.add(obj[i]);
            }
        } else if (obj instanceof ArrayList){
            for (var i=0;i<obj.length();i++){
                this.add(obj.get(i));
            }
        }
    };
}
 
function Iterator (arrayList){
    this.arrayList;
    this.index = 0;
    this.hasNext = function (){
        return this.index < this.arrayList.length();
    };
    this.next = function() {
        return this.arrayList.get(index++);
    };
}


// 출처: http://plasticradio.tistory.com/entry/자바스크립트에서의-ArrayList-사용 [플라스틱라디오's 크리에이티브 마인드]