package com.forthenew.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	public abstract Connection makeconnection() throws ClassNotFoundException, SQLException;
}
