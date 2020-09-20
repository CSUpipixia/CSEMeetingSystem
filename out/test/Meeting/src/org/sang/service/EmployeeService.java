package org.sang.service;

import org.sang.bean.Employee;
import org.sang.dao.EmployeeDao;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();
    private Employee loginUser;
    public List<Employee> searchEmp(String employeename, String username, int page, int count) {
        return employeeDao.searchEmp(employeename, username, page, count);
    }
    public int getCount(String employeename, String username) {
        return employeeDao.getCount(employeename, username);
    }
    public Employee getLoginUser() {
        return loginUser;
    }

    public int updateEmpCreditsById(int id, int credits) {
        return employeeDao.updateEmpCreditsById(id, credits);
    }

    public int login(String username, String password) {
        int result = 0;//表示登录失败
        Employee loginEmp = employeeDao.login(username, password);
        if (loginEmp == null) {
            return result;
        }else{
            this.loginUser = loginEmp;
            return 1;
        }
    }

    public int changePasswordById(int userid, String password) {
        return employeeDao.changePasswordById(userid, password);
    }

    public List<Employee> getEmpByDepId(int depId) {
        return employeeDao.getEmpByDepId(depId);
    }
}
