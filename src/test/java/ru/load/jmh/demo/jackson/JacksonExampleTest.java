package ru.load.jmh.demo.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import ru.load.jmh.demo.jackson.data.User;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;



@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@Measurement(iterations = 1,  time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class JacksonExampleTest  {
	private JacksonExample jacksonExample = new JacksonExample();;

	@BeforeEach
	@Setup
	public void setUp() throws Exception {
		//jacksonExample = new JacksonExample();
	}

	@DisplayName("Should be Baeldung")
	@Test
	@Benchmark
 	public User getUserTest() {
		User result = jacksonExample.getUser();
		assertEquals("Baeldung", result.getName());
		return result;
	}

	@DisplayName("Should be Baeldung")
	@Test
	@Benchmark
 	public User getUserWithCacheTest() {
		User result = jacksonExample.getUserWithCache();
		assertEquals("Baeldung", result.getName());
		return result;
	}


}