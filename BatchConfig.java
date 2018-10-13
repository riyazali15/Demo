package com.javainuse.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.javainuse.listener.JobCompletionListener;
import com.javainuse.model.HolidayBO;
import com.javainuse.step.DeliquencyReportPojo;




@Configuration
public class BatchConfig {
	

	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public HolidayBO holidayBO;
	@Autowired
	public DeliquencyReportPojo deliquencyReportBO;

	private String[] fields = { "Date",
			"Day",
			"Description"};
	private String[] outputFields = { "ID",
			"RPTY_CYCLE",
			"DATE","FLAG_HLDY","DD_SEQ","FNL_SEQ"};
	
	
	@Bean
	public Job processJob() throws Exception {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() throws Exception{
		return stepBuilderFactory.get("orderStep1").<HolidayBO, DeliquencyReportPojo> chunk(1)
				.reader(reader()).processor(processor(holidayBO,deliquencyReportBO))
				.writer(writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	@Bean
	public FlatFileItemReader<HolidayBO> reader() throws Exception
	{
		FlatFileItemReader<HolidayBO> fileItemReader = new FlatFileItemReader<HolidayBO>();
		fileItemReader.setResource(new FileSystemResource("C:\\Users\\shaik\\Desktop\\Support\\springbootBatch\\src\\main\\resources\\Batch_Input.csv"));
		fileItemReader.setLineMapper(new DefaultLineMapper<HolidayBO>() 
		{{
				setLineTokenizer(new DelimitedLineTokenizer() {{setNames(fields);}});
				
				setFieldSetMapper(new BeanWrapperFieldSetMapper<HolidayBO>() {{setTargetType(HolidayBO.class);}});
		}});
		
		return  fileItemReader;
	}
	
	@Bean
	public ItemWriter<DeliquencyReportPojo> writer() throws Exception
	{
		FlatFileItemWriter<DeliquencyReportPojo> fileItemWriter = new FlatFileItemWriter<DeliquencyReportPojo>();
		fileItemWriter.setResource(new FileSystemResource("C:\\Users\\shaik\\Desktop\\Support\\springbootBatch\\src\\main\\resources\\Batch_Output.csv"));
		fileItemWriter.setAppendAllowed(true);
		DelimitedLineAggregator<DeliquencyReportPojo> delimitedLineAggregator = new DelimitedLineAggregator<DeliquencyReportPojo>();
		delimitedLineAggregator.setDelimiter(",");
		BeanWrapperFieldExtractor<DeliquencyReportPojo> fieldExtractor = new BeanWrapperFieldExtractor<DeliquencyReportPojo>(); 
		fieldExtractor.setNames(outputFields);
		delimitedLineAggregator.setFieldExtractor(fieldExtractor);
		fileItemWriter.setLineAggregator(delimitedLineAggregator);
		return  fileItemWriter;
	}
	@Bean
	public ItemProcessor<HolidayBO, DeliquencyReportPojo> processor(HolidayBO item,DeliquencyReportPojo bo) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("From: Processor"+item.toString());
		return null;
	}
}