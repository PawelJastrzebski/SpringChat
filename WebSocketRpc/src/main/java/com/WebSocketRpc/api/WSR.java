package com.WebSocketRpc.api;

import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.WebSocketRpc.application.services.ProcedureExecutor;
import com.WebSocketRpc.application.services.WSRWebSocketHandler;
import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.WebSocketRpc.domain.ports.SessionRepository;
import com.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import com.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.lang.reflect.Field;

/**
 *
 * @param <LT> Local type
 * @param <RT> Remote type
 * @param <I> ID type
 */
public class WSR<LT extends Enum<LT>,RT extends Enum<RT>,I extends Comparable<I>> {

    private TextWebSocketHandler textWebSocketHandler;
    private ProcedureRepository<LT> procedureRepository;
    private SessionRepository<RT,I> sessionRepository;

    private ProcedureDTOConverter<LT> procedureDTOConverter;
    private ProcedureExecutor<LT, RT> procedureExecutor;


    public WSR(Class<LT> localType, Class<RT> remoteType) {


        this.procedureRepository = new ProcedureRepositoryInMemory<>();
        this.sessionRepository = new SessionRepositoryInMemory<>();
        this.procedureDTOConverter = new ProcedureDTOConverter<>(procedureRepository,localType);
        this.procedureExecutor = ProcedureExecutor.configure(procedureRepository);
        this.textWebSocketHandler = WSRWebSocketHandler.configure(sessionRepository,procedureDTOConverter,procedureExecutor);
    }
    /**
     *
     * @param procedureType ID of procedure
     * @param dataType data type pass to procedure
     * @param method  method to execute for WebClient
     * @param <D> ID procedure type
     */
    public <D> void addProcedure(LT procedureType, Class<D> dataType, ProcedureMethod<RT,I,D> method ){

        procedureType.name();

        System.out.println("Add procdure Facade");
        this.procedureRepository.addProcedure(new Procedure<>(procedureType,method,dataType));
    }

    public Session<RT,I> findSession(I id){
        return this.sessionRepository.getSession(id);
    }

    public TextWebSocketHandler getHandler(){
        return this.textWebSocketHandler;
    }

//    public static void main(String[] args) {
//
//
//        final WSR<WSR.LT, WSR.RT, String> WSR = new WSR<>(com.WebSocketRpc.api.WSR.LT.class, com.WebSocketRpc.api.WSR.RT.class);
//
//        WSR.addProcedure(com.WebSocketRpc.api.WSR.LT.AUTHORIZED,String.class,(data, session) -> {
//
//        });
//
//
//    }
//
//    public enum LT{
//        AUTHORIZED, FOWARDMESSAGE
//    }
//
//    public enum RT{
//        ADDMESSAGE, ERROR
//    }

}
