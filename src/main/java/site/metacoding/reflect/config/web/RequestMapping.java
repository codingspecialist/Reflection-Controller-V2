package site.metacoding.reflect.config.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 타겟이 없으면 디폴트는 모든 곳!! (메서드, 클래스, 필드 등등)
@Retention(RetentionPolicy.RUNTIME) // RUNTIME (실행시),SOURCE (컴파일시) 
public @interface RequestMapping {
	String value(); // 어노테이션의 value 메서드가 디폴트 키 값이다. (키 생략가능)
}
