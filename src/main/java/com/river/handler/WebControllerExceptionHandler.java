package com.river.handler;

import com.alibaba.fastjson.JSONObject;
import com.river.common.ResEnum;
import com.river.model.dto.WebDTO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * create by river  2017/9/30
 * desc:
 */
@Component
@Aspect
public class WebControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebControllerExceptionHandler.class);

    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.river.controller..*.*(..))")
    public void controllerPoint() {

    }

    @Around("controllerPoint() && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public String doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("切面【doAroundAdvice】方法【" + proceedingJoinPoint.getSignature().getName() +"】执行");
        WebDTO<Object> dto = new WebDTO<Object>();
        String result = null;
        try {
            //方法组参数
            Object[] args = proceedingJoinPoint.getArgs();
            BindingResult bindingResult = null;
            for(Object arg : args) {
                //判断参数是否是BindingResult类型
                if(arg instanceof BindingResult) {
                    //判断是否有错
                    if(((BindingResult) arg).hasErrors()) {
                        bindingResult = (BindingResult) arg;
                        break;
                    }
                }
            }

            if(null != bindingResult && bindingResult.hasErrors()) {
                catchValidateError(bindingResult,dto);
            }else {
                //调用执行目标方法
                result = (String) proceedingJoinPoint.proceed();
            }
        } catch (ServiceException e) {
            logger.error("业务异常，异常信息：", e);
            dto.setResCode(e.getCode());
            dto.setResMsg(e.getMessage());
        }catch (Exception e) {
            logger.error("系统异常，异常信息：", e);
            dto.setResCode(ResEnum.SYS_ERROR.getResCode());
        }

        return StringUtils.isBlank(result) ? JSONObject.toJSONString(dto) : result;
    }


    /**
     * 处理bindingResult参数异常，只传出第一个错误（多个暂时不考虑）
     * @param bindingResult
     * @param dto
     */
    private void catchValidateError(BindingResult bindingResult, WebDTO<Object> dto) {
        List<ObjectError> list = bindingResult.getAllErrors();
        ObjectError objectError = list.get(0);
        dto.setResCode(ResEnum.PARAM_EXCEPTION.getResCode());
        dto.setResMsg(objectError.getDefaultMessage());
        logger.error("objectName=" + objectError.getObjectName() + ",code=" + objectError.getCode() + ",defaultMessage=" + objectError.getDefaultMessage());
    }

}
