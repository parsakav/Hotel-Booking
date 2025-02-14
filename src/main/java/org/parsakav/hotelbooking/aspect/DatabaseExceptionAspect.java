package org.parsakav.hotelbooking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.parsakav.hotelbooking.exceptions.DatabaseException;
import org.parsakav.hotelbooking.exceptions.NoAvailableRoomException;
import org.parsakav.hotelbooking.exceptions.NotIdProvidedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Aspect
@Component
public class DatabaseExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseExceptionAspect.class);

    @Around("execution(* org.parsakav.hotelbooking.service.*.*(..))")
    public Object handleDatabaseExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        }
        catch (NotIdProvidedException e){
            logger.error(" Id not provided for update operation ");
            throw new DatabaseException(" Id not provided for update operation",e);

        }
        catch (NoAvailableRoomException e){
            logger.error("No available room",e);
            throw new DatabaseException("No available room",e);
        }
        catch (DuplicateKeyException e){
            logger.error("Duplicate Key",e);
            throw new DatabaseException("Duplicate Key", e);
        }
        catch (DataIntegrityViolationException e) {
            logger.error("Database integrity error {}", e.getMessage());
            throw new DatabaseException("Data isn't valid",e);
        } catch (SQLException e) {
            logger.error("SQL Error: {}", e.getMessage());
            throw new DatabaseException("Database error : An error occured.",e);
        } catch (DataAccessException e) {
            logger.error("Data Access Error: {}", e.getMessage());
            throw new DatabaseException("Data Access Error",e);
        }
    }
}