USE [master]
GO
/****** Object:  Database [STO]    Script Date: 28.11.2022 12:29:34 ******/
CREATE DATABASE [STO]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Users', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Users.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Users_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Users_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [STO] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [STO].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [STO] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [STO] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [STO] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [STO] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [STO] SET ARITHABORT OFF 
GO
ALTER DATABASE [STO] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [STO] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [STO] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [STO] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [STO] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [STO] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [STO] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [STO] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [STO] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [STO] SET  DISABLE_BROKER 
GO
ALTER DATABASE [STO] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [STO] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [STO] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [STO] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [STO] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [STO] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [STO] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [STO] SET RECOVERY FULL 
GO
ALTER DATABASE [STO] SET  MULTI_USER 
GO
ALTER DATABASE [STO] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [STO] SET DB_CHAINING OFF 
GO
ALTER DATABASE [STO] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [STO] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [STO] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [STO] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'STO', N'ON'
GO
ALTER DATABASE [STO] SET QUERY_STORE = OFF
GO
USE [STO]
GO
/****** Object:  Table [dbo].[Cars]    Script Date: 28.11.2022 12:29:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cars](
	[StateNum] [nvarchar](100) NOT NULL,
	[VIN] [nvarchar](100) NOT NULL,
	[Brand] [nvarchar](100) NOT NULL,
	[Model] [nvarchar](100) NOT NULL,
	[CarID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
 CONSTRAINT [PK_Cars] PRIMARY KEY CLUSTERED 
(
	[CarID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clients]    Script Date: 28.11.2022 12:29:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clients](
	[ClientID] [int] IDENTITY(1,1) NOT NULL,
	[MobNum] [nvarchar](100) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Surname] [nvarchar](100) NOT NULL,
	[Patronymic] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Clients] PRIMARY KEY CLUSTERED 
(
	[ClientID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lifts]    Script Date: 28.11.2022 12:29:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lifts](
	[LiftType] [nvarchar](50) NOT NULL,
	[LiftID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Lifts] PRIMARY KEY CLUSTERED 
(
	[LiftID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 28.11.2022 12:29:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedule](
	[ScheduleID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
	[CarID] [int] NOT NULL,
	[LiftID] [int] NOT NULL,
	[StartDate] [datetime] NOT NULL,
	[Duration] [float] NOT NULL,
	[Comment] [nvarchar](3000) NULL,
	[Mileage] [int] NULL,
 CONSTRAINT [PK_Schedule] PRIMARY KEY CLUSTERED 
(
	[ScheduleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 28.11.2022 12:29:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Login] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Role] [bit] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Cars]    Script Date: 28.11.2022 12:29:34 ******/
CREATE NONCLUSTERED INDEX [IX_Cars] ON [dbo].[Cars]
(
	[StateNum] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Cars]  WITH CHECK ADD  CONSTRAINT [FK_Cars_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Cars] CHECK CONSTRAINT [FK_Cars_Clients]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Schedule_Cars] FOREIGN KEY([CarID])
REFERENCES [dbo].[Cars] ([CarID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Schedule_Cars]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Schedule_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Schedule_Clients]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Schedule_Lifts] FOREIGN KEY([LiftID])
REFERENCES [dbo].[Lifts] ([LiftID])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Schedule_Lifts]
GO
USE [master]
GO
ALTER DATABASE [STO] SET  READ_WRITE 
GO
