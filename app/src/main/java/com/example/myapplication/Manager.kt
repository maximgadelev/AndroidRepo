package com.example.myapplication

class Manager(name: String,
              surname :String,
              workerId:Int,
              isOnWork:Boolean,
              salary:Int,
              var nameOfOffice:String
):Worker(name,surname,workerId,isOnWork,salary),ManagerInterfacce {


    override fun toManageWorker(worker: Worker): String {
        var decision=""
       if(worker.isOnWork==false){
           decision="You are dismiss!"
       }else{
         decision="You are very good worker!"
       }
        return decision
    }

    override fun toString(): String {
        return "Manager(nameOfOffice='$nameOfOffice') ${super.toString()}"
    }

}
