package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import dao.Conexao;

public class CriarTabelas {

	public static void main(String[] args) throws SQLException {

		String droparTabelas = "drop table if exists producer;" + " drop table if exists movie;" + " drop table if exists studio;" + " drop table if exists MOVIE_PRODUCER;" + " drop table if exists MOVIE_STUDIO;" ;
		String tabbleStudio = "create table STUDIO(studio_pk int, name varchar(50), primary key (studio_pk));";
		String tabbleProducer = "create table PRODUCER (producer_pk int, name varchar(50), primary key (producer_pk));";
		String tabbleMovie = "create table MOVIE (movie_pk int,title varchar(50),year int, winner char,primary key (movie_pk));";
		String tabbleMovieProducer = "create table MOVIE_PRODUCER (movie_fk int, producer_fk int, primary key (movie_fk,producer_fk ), foreign key (movie_fk) references movie (movie_pk), foreign key (producer_fk) references producer (producer_pk) ON DELETE CASCADE);";
		String tabbleMovieStudio = "create table MOVIE_STUDIO (movie_fk int, studio_fk int, primary key (movie_fk,studio_fk ), foreign key (movie_fk) references movie (movie_pk), foreign key (studio_fk) references studio (studio_pk) ON DELETE CASCADE);";
		Connection conexao = Conexao.obterConexao();

		CallableStatement myStmt;

		myStmt = conexao.prepareCall(
				droparTabelas + tabbleStudio + tabbleProducer + tabbleMovie + tabbleMovieProducer + tabbleMovieStudio);
		myStmt.execute();
		System.out.println("Foram criadas as tabelas do banco.");
		conexao.close();

	}

}
