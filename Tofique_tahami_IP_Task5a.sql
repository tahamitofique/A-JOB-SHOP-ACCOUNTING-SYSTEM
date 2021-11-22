
1-- Creating a procedure Customer_Insert which inserts a new customer in Customer table 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Customer_Insert]
    @customer_name VARCHAR(30),
    @customer_address VARCHAR(100),
    @category INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Customer VALUES(@customer_name,@customer_address,@category);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Customer_Insert
GO 
EXEC Customer_Insert @customer_name='naeem', @customer_address='brooks street', @category = 5;

select * from customer;


2-- Creating a procedure Department_Insert which inserts a new department in Department table 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Department_Insert]
    @department_num INT,
    @details VARCHAR(100)
AS
BEGIN
    BEGIN TRY
        INSERT INTO Department VALUES(@department_num,@details);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Department_Insert
GO 
EXEC Department_Insert @department_num= 27,@details='Paint Department';

select * from Department;

3-- Creating a procedure Process-Department_Insert which inserts a new process with department
--Fit Process

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Fit_Process_Department_Insert]
    @p_id INT,
    @department_num INT,
    @fit_type varchar(20)
AS
BEGIN
    BEGIN TRY
        INSERT INTO Process (pid,d_num) VALUES(@p_id,@department_num);
        INSERT INTO fit VALUES(@p_id,@fit_type);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Cut Process-Department_Insert
GO 
EXEC Fit_Process_Department_Insert 1,24,'over fitting';

select * from Process;
select * from fit;

--Paint Process

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Paint_Process_Department_Insert]
    @p_id INT,
    @department_num INT,
    @paint_type varchar(20),
    @method varchar(25)
AS
BEGIN
    BEGIN TRY
        INSERT INTO Process (pid,d_num) VALUES(@p_id,@department_num);
        INSERT INTO Paint VALUES(@p_id,@paint_type,@method);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Paint Process-Department_Insert
GO 
EXEC Paint_Process_Department_Insert 2,24,'oven paint','bake paint';

select * from Process;
select * from paint;


--Cut Process

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Cut_Process_Department_Insert]
    @p_id INT,
    @department_num INT,
    @cut_type varchar(20),
    @machine_type varchar(20)
AS
BEGIN
    BEGIN TRY
        INSERT INTO Process (pid,d_num) VALUES(@p_id,@department_num);
        INSERT INTO cut VALUES(@p_id,@cut_type,@machine_type);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Cut Process-Department_Insert
GO 
EXEC Cut_Process_Department_Insert 3,24,'sheet cut','saw';

select * from Process;
select * from cut;

4-- Creating a procedure Assembly_Insert which inserts a new assembly and associates with a process 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Assembly_Insert]
    @id INT,
    @date date,
    @details varchar(150),
    @customer_name varchar(30),
    @pid INT    
AS
BEGIN
    BEGIN TRY
        INSERT INTO Assembly(id,date,details,name) VALUES(@id,@date,@details,@customer_name);
        Insert INTO manufacture(id,pid) VALUES(@id,@pid);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Assembly_Insert
GO 
EXEC Assembly_Insert 39,'2021/8/11','kitchen renovation','sheikh',2;

select * from Assembly;
select * from manufacture;



insert into Account VALUES
(00413,'2021/6/12');

5-- Creating a procedure Account_Insert which inserts a new account in respeective Department 
--Department Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Account_Insert]
    @num INT,
    @date date,
    @details varchar(100),
    @cost INT,
    @d_num INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Account VALUES(@num,@date);
        INSERT INTO Department_Acc VALUES(@num,@details,@cost);
        Insert INTO Used_record_costs_3(num,d_num) Values(@num,@d_num);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Account_Insert
GO 
EXEC Account_Insert 125,'2021/6/7','FIrst Deposit',400,@d_num=24;

select * from Account;
select * from Department_Acc;
select * from Used_record_costs_3;

--Process Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Process_Account_Insert]
    @num INT,
    @date date,
    @details varchar(100),
    @cost INT,
    @pid INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Account VALUES(@num,@date);
        INSERT INTO Process_Acc VALUES(@num,@details,@cost);
        Insert INTO Used_record_costs_1 Values(@num,@pid);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Process_Account_Insert
GO 
EXEC Process_Account_Insert 145,'2021/6/7','FIrst Deposit',500,1;

select * from Account;
select * from process_Acc;
select * from Used_record_costs_1;

--Assembly Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Assembly_Account_Insert]
    @num INT,
    @date date,
    @details varchar(100),
    @cost INT,
    @id INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Account VALUES(@num,@date);
        INSERT INTO Assembly_Acc VALUES(@num,@details,@cost);
        Insert INTO Used_record_costs_2 Values(@num,@id);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Assembly_Account_Insert
GO 
EXEC Assembly_Account_Insert 195,'2021/6/7','FIrst Deposit',500,14;

select * from Account;
select * from Assembly_Acc;
select * from Used_record_costs_1;


6-- Creating a procedure Job_Insert which inserts a new Job in Job table 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Job_Insert]
    @job_no INT,
    @start_date date,
    @pid INT,
    @id INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Job VALUES(@job_no,@start_date, NULL,@pid,@id);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure JOB_Insert
GO 
EXEC Job_Insert 3,'2021/2/2',2,39;

select * from job;


7-- Creating a procedure Job_Close which closes the Job in Job table 
--Cut Job

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Cut_Job_close]
    @job_no INT,
    @end_date date,
    @type varchar(15),
    @amt_of_time INT,
    @material varchar(15),
    @labor_time INT
AS
BEGIN
    BEGIN TRY
        Update Job SET end_date =@end_date where job_no =@job_no;
        INSERT INTO cut_job VALUES(@job_no,@type,@amt_of_time,@material,@labor_time);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Cut_Job_close
GO 
EXEC Cut_Job_close 1,'2021/9/9','cut job',90,'wood',39;


select * from job;
select * from Cut_job;


--Paint Job

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Paint_Job_close]
    @job_no INT,
    @end_date date,
    @labor_time INT,
    @volume INT,
    @colour varchar(15)
    
AS
BEGIN
    BEGIN TRY
        Update Job SET end_date =@end_date where job_no =@job_no;
        INSERT INTO Paint_job VALUES(@job_no,@labor_time,@volume,@colour);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Paint_Job_close
GO 
EXEC Paint_Job_close 2,'2021/9/9',90,39,'black';

select * from job;
select * from paint_job;

--Fit Job

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Fit_Job_close]
    @job_no INT,
    @end_date date,
    @labor_time INT
    
AS
BEGIN
    BEGIN TRY
        Update Job SET end_date =@end_date where job_no =@job_no;
        INSERT INTO Fit_job VALUES(@job_no,@labor_time);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Fit_Job_close
GO 
EXEC Fit_Job_close 3,'2021/9/9',90;

select * from job;
select * from Fit_job;

8-- Creating a procedure Transactions which inserts cost and updates all affected accounts
--Assembly Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Assembly_add]
    @transaction_no Int,
    @amount INT,
    @num INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Transactionn VALUES (@transaction_no, @amount, NULL);
        INSERT INTO Updatee VALUES (@num,@transaction_no);
        UPDATE Assembly_Acc SET cost += @amount WHERE num =@num;


    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Assembly_add
GO 
EXEC Assembly_add 105,200,195;

SELECT * FROM Transactionn;
SELECT * FROM Assembly_Acc;
SELECT * FROM Updatee;


--Department Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Department_add]
    @transaction_no Int,
    @amount INT,
    @num INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Transactionn VALUES (@transaction_no, @amount, NULL);
        INSERT INTO Updatee VALUES (@num,@transaction_no);
        UPDATE department_Acc SET cost += @amount WHERE num =@num;


    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Department_add
GO 
EXEC Department_add 115,250,125;

SELECT * FROM Transactionn;
SELECT * FROM department_Acc;
SELECT * FROM Updatee;


--Process Account

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Process_add]
    @transaction_no Int,
    @amount INT,
    @num INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Transactionn VALUES (@transaction_no, @amount, NULL);
        INSERT INTO Updatee VALUES (@num,@transaction_no);
        UPDATE process_Acc SET cost += @amount WHERE num =@num;


    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Process_add
GO 
EXEC Process_add 177,450,145;

SELECT * FROM Transactionn;
SELECT * FROM process_Acc;
SELECT * FROM Updatee;


9-- Creating a procedure Total_cost which restrives cost on particular id 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Total_cost]
    @id INT
AS
BEGIN
    BEGIN TRY
        Select cost from Assembly_Acc where num IN(select num from Used_record_costs_2 where id =@id);
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Total_cost
GO 
EXEC Total_cost 14;


10-- Creating a procedure Total_time which restrives total labor time within a department completed on particular date 

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Total_time]
    @date date,
    @d_num INT
AS
BEGIN
    BEGIN TRY
        select sum(labor_time) from (select sum(labor_time) as Labor_Time from Fit_job where Job_no in (select a.Job_No from Job a join Process p  on a.pid=p.pid join Job j ON a.Job_No=j.Job_No
         where d_num=@d_num and end_date=@date)
        UNION
        select sum(Labour_Time) as Labour_Time from Paint_job where Job_no in (select a.Job_No from Assign a join Process p  on a.Process_ID=p.Process_id join Job j ON a.Job_No=j.Job_No
        where Department_ID=@Department_id and Completed_date=@Cdate)
        UNION
        select sum(Labour_Time) as Labour_Time from Cut_job where Job_no in (select a.Job_No from Assign a join Process p  on a.Process_ID=p.Process_id join Job j ON a.Job_No=j.Job_No
        where Department_ID=@Department_id and Completed_date=@Cdate)

    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Total_cost
GO 
EXEC Total_cost 14;

    select * from Department;
    select * from process;
    select * from job;


    select a.Job_No from Job a join Process p  on a.pid=p.pid where d_num=24 and end_date=@date;




11-- Creating a procedure Processes which restrives process on given assembly id in date order

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Processes]
    @id INT
AS
BEGIN
    BEGIN TRY
        select pid, d_num from Process where pid IN(select pid from manufacture where id IN (select id from Assembly where id =@id order by date));
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Processes
GO 
EXEC Processes 39;

12-- Creating a procedure Jobs which restrives job on given date and department
--Cut Jobs
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Cut_Jobs]
    @end_date date,
    @d_num Int
AS
BEGIN
    BEGIN TRY
        select j.job_no, j.id, c.type, c.amt_of_time, c.material, c.labor_time 
    from job j 
    join cut_job c on j.job_no=c.job_no
    where end_date=@end_date AND pid IN(select pid from Process where d_num=@d_num);
    
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Jobs
GO 
EXEC Cut_Jobs '2021/9/9',24;


--Paint Jobs
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Paint_Jobs]
    @end_date date,
    @d_num Int
AS
BEGIN
    BEGIN TRY
        select j.job_no, j.id, p.colour, p.volume, p.labor_time 
    from job j 
    join Paint_job p on j.job_no=p.job_no
    where end_date=@end_date AND pid IN(select pid from Process where d_num=@d_num);
    
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Paint_Jobs
GO 
EXEC Paint_Jobs '2021/9/9',24;




--Fit Jobs
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Fit_Jobs]
    @end_date date,
    @d_num Int
AS
BEGIN
    BEGIN TRY
        select j.job_no, j.id, f.labor_time 
    from job j 
    join Fit_job f on j.job_no=f.job_no
    where end_date=@end_date AND pid IN(select pid from Process where d_num=@d_num);
    
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Fit_Jobs
GO 
EXEC Fit_Jobs '2021/9/9',24;


--13 Creating a procedure Categorical_Customer which restrives customer of same category
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Categorical_Customer]
    @category INT
AS
BEGIN
    BEGIN TRY
        select * from Customer where category=@category order by name ASC;
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Categorical_Customer
GO 
EXEC Categorical_Customer 5;










--14 Creating a procedure Remove_Cut_job which delete cut jobs of given range
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Remove_Cut_job]
    @from INT,
    @to INT
AS
BEGIN
    BEGIN TRY
        delete from Cut_job where job_no BETWEEN @from AND @to ;
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Remove_Cut_job
GO 
EXEC Remove_Cut_job 2,3;

select * from Cut_job;






--15 Creating a procedure Change_colour which changes the paint colour
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[Change_colour]
    @from_colour varchar(15),
    @to_colour varchar(15)
AS
BEGIN
    BEGIN TRY
        update Paint_job set colour = @to_colour where colour=@from_colour;
    END TRY
    BEGIN CATCH
        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage;
    END CATCH
END
GO
-- Executing the procedure Change_colour
GO 
EXEC Change_colour black,pink;

select * from Paint_job;



--15
