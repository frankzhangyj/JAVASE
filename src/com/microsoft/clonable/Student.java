package com.microsoft.clonable;

/**
 * 自定义类实现深拷贝需要首先实现Cloneable接口 这个接口只起到标记作用 告诉jvm类中有具体定义的clone方法
 * 然后重新定义clone方法 否则编译时检测不到有clone方法 静态绑定
 * 首先都需要调用Object中的clone方法克隆当前对象 相当于重新构建了一个类 但是其中的引用变量还是和原类中的变量指向地址相同 (浅拷贝)
 * 如果类中成员有引用类型 需要在clone方法中调用引用类的clone方法 (深拷贝)
 */
public class Student implements Cloneable {
    String name;
    Student student;

    /**
     * 注意需要将访问修饰符设置为public 如果是protected 那么就只能在同一包内 或者子类中调用
     * @return cloneable object
     */
    @Override
    public Student clone() {
        try {
            Student newStudent = (Student) super.clone();
            // 检查 student 是否为 null，避免 NullPointerException
            if (this.student != null) {
                newStudent.student = (Student) this.student.clone();
            }
            return newStudent;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // 不应该发生，已实现 Cloneable 接口
        }
    }
}
