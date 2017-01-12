package com.example.shdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

public class ArticleManager {
	
	void addUniqueAbility(UniqueAbility uniqueAbility);
	
}
