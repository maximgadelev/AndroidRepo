package com.example.myapplication

class ProjectWorker(name: String,
                     surname :String,
                     workerId:Int,
                     isOnWork:Boolean,
                     salary:Int,
                     var projectId:Int
):Worker(name,surname,workerId,isOnWork,salary),ProjectInterface{
    override fun makeProject():String {
        var decision:String =""
        if(salary==0){
       decision="I will not do the project"
        }else{
            decision="Let's go!"
        }
        return decision
    }

    override fun toString(): String {
        return "ProjectWorker(projectId=$projectId) ${super.toString()}"
    }

}
